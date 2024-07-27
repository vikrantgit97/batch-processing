package com.example.config;

import com.example.entity.Student;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

public class DbToCsvBatchConfig {}
/*

@Configuration
public class DbToCsvBatchConfig {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job runJob() {
        return new JobBuilder("exportStudents", jobRepository)
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
        return new StepBuilder("csvExport", jobRepository)
                .<Student, Student>chunk(10, platformTransactionManager)
                .reader(reader())
                .processor(item -> item)
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    //3. Writer Bean
    @Bean
    public ItemWriter<Student> writer() {
        FlatFileItemWriter<Student> writer = new FlatFileItemWriter<>();
        //File Location + name
//        writer.setResource(new FileSystemResource("/home/user/rara/devraj/students.csv"));
        writer.setResource(new FileSystemResource("src/main/resources/students.csv"));
        //create one line based on one object
        writer.setLineAggregator(new DelimitedLineAggregator<>() {{
            setDelimiter(",");
            //read data from object variable names
            setFieldExtractor(new BeanWrapperFieldExtractor<>() {{
                setNames(new String[]{"stdId", "stdName", "stdFee"});
            }});
        }});
        return writer;
    }


    //1. Reader Bean
    @Bean
    public ItemReader<Student> reader() {
        JdbcCursorItemReader<Student> reader = new JdbcCursorItemReader<>();
        //provide SQL query
        reader.setSql("SELECT stdId,stdName,stdFee FROM student");
        //specify database connection
        reader.setDataSource(dataSource);
        //convert one ResultSet Row into One Student Object
        reader.setRowMapper(
                (rs, rowNum) -> new Student(
                        rs.getInt("stdId"),
                        rs.getString("stdName"),
                        rs.getDouble("stdFee"))
        );
        return reader;
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
}
*/
