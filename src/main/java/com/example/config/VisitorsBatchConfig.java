package com.example.config;

import com.example.dto.VisitorsDto;
import com.example.entity.Visitors;
import com.example.mapper.VisitorsMapper;
import com.example.repositories.VisitorsRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.validation.BindException;

import java.io.IOException;

@Slf4j
@Configuration
public class VisitorsBatchConfig {

    @Bean
    public Job importVistorsJob(
        final JobRepository jobRepository,
        final PlatformTransactionManager transactionManager,
        final VisitorsRepository visitorsRepository,
        final VisitorsMapper visitorsMapper
    ) throws IOException {
        return new JobBuilder("importVisitorsJob", jobRepository)
            .incrementer(new RunIdIncrementer())
            .start(importVisitorsStep(jobRepository, transactionManager, visitorsRepository, visitorsMapper))
            .build();
    }

    @Bean
    public Step importVisitorsStep(
       final JobRepository jobRepository,
       final PlatformTransactionManager transactionManager,
       final VisitorsRepository visitorsRepository,
       final VisitorsMapper visitorsMapper
    ) throws IOException {
        return new StepBuilder("importVisitorsStep", jobRepository)
            .<VisitorsDto, Visitors>chunk(100, transactionManager)
            .reader(flatFileItemReader(null))
            .processor(new ItemProcessor<VisitorsDto, Visitors>() {
                @Override
                public Visitors process(final VisitorsDto visitorsDto) {
                    return visitorsMapper.toVisitors(visitorsDto);
                }
            })
            .writer(new ItemWriter<Visitors>() {
                @Override
                public void write(Chunk<? extends Visitors> visitors) throws Exception {
                    visitors.getItems().forEach(visitorsRepository::save);
                }
            })
        .build();
    }


    @Bean
    @StepScope
    public FlatFileItemReader<VisitorsDto> flatFileItemReader(
            @Value("#{jobParameters['inputFile']}") final String visitorsFile
    ) throws IOException {
        val flatFileItemReader = new FlatFileItemReader<VisitorsDto>();
        flatFileItemReader.setName("VISITORS_READER");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setLineMapper(linMapper());
        flatFileItemReader.setStrict(false);
        flatFileItemReader.setResource(new FileSystemResource(visitorsFile));
        return flatFileItemReader;
    }


    @Bean
    public LineMapper<VisitorsDto> linMapper() {
        val defaultLineMapper = new DefaultLineMapper<VisitorsDto>();
        val lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setNames("visitorId", "firstName", "lastName", "emailAddress", "phoneNumber", "address", "visitDate");
        lineTokenizer.setStrict(false);
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(new FieldSetMapper<VisitorsDto>() {

            @Override
            public VisitorsDto mapFieldSet(final FieldSet fieldSet) throws BindException {
                return VisitorsDto.builder()
                    .visitorId(fieldSet.readLong("visitorId"))
                    .firstName(fieldSet.readString("firstName"))
                    .lastName(fieldSet.readString("lastName"))
                    .emailAddress(fieldSet.readString("emailAddress"))
                    .phoneNumber(fieldSet.readString("phoneNumber"))
                    .address(fieldSet.readString("address"))
                    .visitDate(fieldSet.readString("visitDate"))
                .build();
            }
        });
        return defaultLineMapper;

    }

}
