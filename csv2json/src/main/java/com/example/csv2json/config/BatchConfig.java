package com.example.csv2json.config;

import com.example.csv2json.batchReaders.StackRLanAnswersReader;
import com.example.csv2json.batchWriters.StackRLanItemWriter;
import com.example.csv2json.listener.BatchStatusListener;
import com.example.csv2json.model.StackRLanAnswers;
import com.example.csv2json.processor.StackRLanAnswersProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public ItemReader reader() {
        return new StackRLanAnswersReader().reader();
    }

    @Bean
    public ItemProcessor processor() {
        return new StackRLanAnswersProcessor();
    }

    @Bean
    public ItemWriter writer() {
        return new StackRLanItemWriter(mongoTemplate).writer();
    }

    @Bean
    public Job importCsvJob(BatchStatusListener batchStatusListener, Step importCsvStep) {
        return jobBuilderFactory.get("importCsvJob")
                .incrementer(new RunIdIncrementer())
                .listener(batchStatusListener)
                .flow(importCsvStep)
                .end()
                .build();
    }

    @Bean
    public Step importCsvStep() {
        return stepBuilderFactory.get("importCsvStep")
                .<StackRLanAnswers, StackRLanAnswers> chunk(100)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
}
