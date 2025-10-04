package com.example.Askify.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Askify.Model.Question;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface QuestionRepository extends ReactiveMongoRepository<Question, String> {
    @Query("{ '$or': [ {'title':  { $regex: ?0, $options: 'i' } }, { 'content': { $regex: ?0, $options: 'i' } } ] }")
    Flux<Question> findByTitleOrContent(String searchTerm, Pageable pageable);

    @Query(value = "{ '$or': [ {'title':  { $regex: ?0, $options: 'i' } }, { 'content': { $regex: ?0, $options: 'i' } } ] }", count = true)
    Mono<Long> countByTitleContainingOrContentContaining(String searchTerm);

    Mono<Question> findById(String id); 
}
