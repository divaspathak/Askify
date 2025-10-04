package com.example.Askify.Mapper;

import com.example.Askify.Dto.QuestionResponseDto;
import com.example.Askify.Model.Question;

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
}
