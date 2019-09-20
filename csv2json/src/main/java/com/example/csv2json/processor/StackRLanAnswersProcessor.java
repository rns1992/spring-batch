package com.example.csv2json.processor;

import com.example.csv2json.model.StackRLanAnswers;
import org.springframework.batch.item.ItemProcessor;

public class StackRLanAnswersProcessor implements ItemProcessor<StackRLanAnswers, StackRLanAnswers> {

    @Override
    public StackRLanAnswers process(StackRLanAnswers stackRLanAnswers) throws Exception {
        return stackRLanAnswers;
    }
}
