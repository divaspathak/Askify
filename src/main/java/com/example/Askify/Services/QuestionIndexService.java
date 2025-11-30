package com.example.Askify.Services;

import org.springframework.stereotype.Service;

import com.example.Askify.Model.Question;
import com.example.Askify.Model.QuestionElasticDocument;
import com.example.Askify.Repository.QuestionDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionIndexService implements IQuestionIndexService {

    private final QuestionDocumentRepository questionDocumentRepository;

    @Override
    public void createQuestionIndex(Question question){

        QuestionElasticDocument questionElasticDocument = QuestionElasticDocument.builder()
            .id(question.getId())
            .title(question.getTitle())
            .content(question.getContent())
            .createdAt(question.getCreatedAt())
            .views(question.getViews())   
            .build();

        questionDocumentRepository.save(questionElasticDocument); 
    }
}
