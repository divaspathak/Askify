package com.example.Askify.Repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.Askify.Model.Like;

public interface LikeRepository extends ReactiveMongoRepository<Like, String> {
    
}
