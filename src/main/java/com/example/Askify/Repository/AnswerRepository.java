package com.example.Askify.Repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Askify.Model.Answer;

import reactor.core.publisher.Flux;

@Repository
public interface AnswerRepository extends ReactiveMongoRepository<Answer, String> {

    @Query("{'questionId' : ?0}")
    Flux <Answer> findByQuestionId(String questionId);
}
