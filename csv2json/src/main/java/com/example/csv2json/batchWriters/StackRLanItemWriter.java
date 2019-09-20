package com.example.csv2json.batchWriters;

import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.data.mongodb.core.MongoTemplate;

public class StackRLanItemWriter {

    private MongoTemplate mongoTemplate;

    public StackRLanItemWriter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public MongoItemWriter writer() {
        MongoItemWriter mongoItemWriter = new MongoItemWriter();
        mongoItemWriter.setCollection("stackRLanAnswers");
        mongoItemWriter.setTemplate(mongoTemplate);
        return mongoItemWriter;
    }

}
