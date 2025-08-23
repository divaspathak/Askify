package com.example.Askify.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.Askify.Model.Question;

public interface IQuestionRespository extends ReactiveMongoRepository<Question, String> {
    //Flux<Question> findByAuthorId(String authorId);
    
    //Mono<Long> countByAuthorId(String authorId);
}
