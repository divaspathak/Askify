package com.example.Askify.Services;
import com.example.Askify.Dto.QuestionListResponseDto;
import com.example.Askify.Dto.QuestionRequestDto;
import com.example.Askify.Dto.QuestionResponseDto;
import reactor.core.publisher.Mono; 
public interface IQuestionService {

    //public Flux<QuestionResponseDto> getQuestionsByAuthor(String authorId);
    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDto questionResponse);

    public Mono<QuestionListResponseDto> getAllQuestions(int limit, int offset);

    public Mono<QuestionListResponseDto> searchQuestions(String searchTerm, int limit, int offset);
} 
