package com.example.csv2json.batchReaders;

import com.example.csv2json.model.StackRLanAnswers;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.core.io.ClassPathResource;

public class StackRLanAnswersReader {

    private String[] columnNames = {"Id", "ownerUserId", "creationDate", "parentId", "score", "isAcceptedAnswer", "Body"};

    public FlatFileItemReader<StackRLanAnswers> reader() {
        return new FlatFileItemReaderBuilder<StackRLanAnswers>()
                .name("stackRLanAnswersReader")
                .resource(new ClassPathResource("Answers.csv"))
                .delimited()
                .names(columnNames)
                .fieldSetMapper(fieldSetMapper())
                .build();
    }

    private FieldSetMapper<StackRLanAnswers> fieldSetMapper() {
         return (FieldSet fieldSet) -> {
            StackRLanAnswers stackRLanAnswers = StackRLanAnswers.builder()
                    .id(fieldSet.readInt("Id"))
                    .creationDate(fieldSet.readString("creationDate"))
                    .parentId(fieldSet.readInt("parentId"))
                    .score(fieldSet.readInt("score"))
                    .isAcceptedAnswer(fieldSet.readString("acceptedAnswer"))
                    .body(fieldSet.readString("body"))
                    .build();

            String ownerId = fieldSet.readString("ownerUserId");
            try{
                stackRLanAnswers.setOwnerUserId(Integer.parseInt(ownerId));
            } catch (Exception e) {}

            return stackRLanAnswers;
        };
    }
}
