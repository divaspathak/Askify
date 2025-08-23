package com.example.Askify.Services;

import java.time.LocalDateTime;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.example.Askify.Dto.QuestionListResponseDto;
import com.example.Askify.Dto.QuestionRequestDto;
import com.example.Askify.Dto.QuestionResponseDto;
import com.example.Askify.Mapper.QuestionMapper;
import com.example.Askify.Model.Question;
import com.example.Askify.Repository.IQuestionRespository;
import reactor.core.publisher.Mono;

@Component
public class QuestionService implements IQuestionService {
    private final IQuestionRespository questionRespository;

    public QuestionService(IQuestionRespository questionRespository) {
        this.questionRespository = questionRespository;
    }

    @Override
    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDto questionResponse) {
        Question question = Question.builder()
                .title(questionResponse.getTitle())
                .content(questionResponse.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRespository.save(question)
                .map(QuestionMapper::toQuestionResponseDto)
                .doOnSuccess(response -> System.out.println("Question Created Successfully" + response))
                .doOnError(error -> System.out.println("Errro creating Question" + error));
    }

    public Mono<QuestionListResponseDto> getAllQuestions(int limit, int offset) {
        return questionRespository.findAll((Sort.by(Sort.Direction.ASC, "createdAt")))
                .skip(offset).take(limit)
                .map(QuestionMapper::toQuestionResponseDto)
                .collectList()
                .map(list -> new QuestionListResponseDto(list, list.size()))
                .doOnSuccess(result -> System.out.println("Fetched all questions successfully"))
                .doOnError(error -> System.out.println("Error fetching questions: " + error));
    }
}
