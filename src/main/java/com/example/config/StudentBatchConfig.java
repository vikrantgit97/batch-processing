package com.example.config;

import com.example.entity.Product;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;


@Configuration
public class StudentBatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job runJob() {
        return new JobBuilder("importStudents", jobRepository)
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
        return new StepBuilder("csvImport", jobRepository)
            .<Product, Product>chunk(10, platformTransactionManager)
            .reader(reader())
            .processor(item -> {
                item.setGst(item.getProdCost() * 12 / 100.0);
                item.setDisc(item.getProdCost() * 20 / 100.0);
                return item;
            })
            .writer(writer())
            .taskExecutor(taskExecutor())
            .build();
    }

    @Bean
    public FlatFileItemReader<Product> reader() {
        FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("product.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<Product>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("prodId", "prodCode", "prodCost");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {{
                setTargetType(Product.class);

            }});
        }});
        return reader;
    }


    @Bean
    public JdbcBatchItemWriter<Product> writer() {
        JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(dataSource);
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
        writer.setSql("INSERT INTO product (pid,pcode,pcost,gst,disc) VALUES (:prodId,:prodCode,:prodCost,:gst,:disc)");
        return writer;
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}