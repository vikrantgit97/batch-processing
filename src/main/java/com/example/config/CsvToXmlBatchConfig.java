package com.example.config;

import com.example.entity.Company;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CsvToXmlBatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Bean
    public Job runJob() {
        return new JobBuilder("exportToXml", jobRepository)
            .start(stepA())
            .listener(
                new JobExecutionListener() {

                    public void beforeJob(JobExecution je) {
                        System.out.println("STARTED WITH =>" + je.getStatus());
                    }

                    public void afterJob(JobExecution je) {
                        System.out.println("FINISHED WITH =>" + je.getStatus());
                    }
                }
            )
        .build();
    }


    @Bean
    public Step stepA() {
        return new StepBuilder("csvExportXml", jobRepository)
            .<Company, Company>chunk(10, platformTransactionManager)
            .reader(reader())
            .processor(item -> item)
            .writer(writer())
            .taskExecutor(taskExecutor())
        .build();
    }

    //1. reader
    @Bean
    public ItemReader<Company> reader() {
        return new FlatFileItemReader<>() {{
            setResource(new FileSystemResource("src/main/resources/company.csv"));
            setLineMapper(new DefaultLineMapper<>() {{
                setLineTokenizer(new DelimitedLineTokenizer() {{
                    setDelimiter(",");
                    setNames("prodId", "prodName", "prodCost");
                }});
                setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                    setTargetType(Company.class);
                }});
            }});
        }};
    }

    //2. processor
    @Bean
    public ItemProcessor<Company, Company> processor() {
        return item -> {
            double cost = item.getProdCost();
            item.setProdCost(cost * 12 / 100);
            item.setProdGst(cost * 18 / 100);
            return item;
        };
    }

    //3. writer
    @Bean
    public ItemWriter<Company> writer() {
        return new StaxEventItemWriter<>() {{
            setResource(new FileSystemResource("src/main/resources/company.xml"));
            setMarshaller(new Jaxb2Marshaller() {{
                setClassesToBeBound(Company.class);
            }});
            setRootTagName("products-info");
        }};
    }

    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}
