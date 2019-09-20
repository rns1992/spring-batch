package com.example.csv2json.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@Document(collection = "stackRLanAnswers")
public class StackRLanAnswers {

    @Id
    private Integer id;
    private Integer ownerUserId;
    private String creationDate;
    private Integer parentId;
    private Integer score;
    private String isAcceptedAnswer;
    private String body;

}
