package com.example.Askify.Mapper;

import com.example.Askify.Dto.QuestionResponseDto;
import com.example.Askify.Model.Question;
import com.example.Askify.Model.QuestionElasticDocument;

public class QuestionMapper {
    public static QuestionResponseDto toQuestionResponseDto(Question question){
        return QuestionResponseDto.builder()
        .id(question.getId())
        .title(question.getTitle())
        .content(question.getContent())
        .createdAt(question.getCreatedAt())
        .views(question.getViews())
        .build(); 
    }

    public static QuestionResponseDto toQuestionResponseDto(QuestionElasticDocument questionElasticDocument){
        return QuestionResponseDto.builder()
        .id(questionElasticDocument.getId())
        .title(questionElasticDocument.getTitle())
        .content(questionElasticDocument.getContent())
        .build();
    }
}
