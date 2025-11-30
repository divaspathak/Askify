package com.example.Askify.Model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "questions")
public class QuestionElasticDocument {
    @Id
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdAt; 
    private Integer views; 
}
