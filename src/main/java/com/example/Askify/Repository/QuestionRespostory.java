package com.example.Askify.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;

import com.example.Askify.Model.Question;

@Component
public interface QuestionRespostory extends ReactiveMongoRepository<Question, String> {
    
}
