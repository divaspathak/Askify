package com.example.Askify.Services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import com.example.Askify.Dto.QuestionListResponseDto;
import com.example.Askify.Dto.QuestionRequestDto;
import com.example.Askify.Dto.QuestionResponseDto;
import com.example.Askify.Mapper.QuestionMapper;
import com.example.Askify.Model.Question;
import com.example.Askify.Repository.QuestionRepository;
import reactor.core.publisher.Mono;

@Component
public class QuestionService implements IQuestionService {
    private final QuestionRepository questionRepository;
    private final ReactiveMongoTemplate template;

    public QuestionService(QuestionRepository questionRespository, ReactiveMongoTemplate template) {
        this.questionRepository = questionRespository;
        this.template = template;
    }

    @Override
    public Mono<QuestionResponseDto> createQuestion(QuestionRequestDto questionResponse) {
        Question question = Question.builder()
                .title(questionResponse.getTitle())
                .content(questionResponse.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        return questionRepository.save(question)
                .map(QuestionMapper::toQuestionResponseDto)
                .doOnSuccess(response -> System.out.println("Question Created Successfully" + response))
                .doOnError(error -> System.out.println("Errro creating Question" + error));
    }

    public Mono<QuestionListResponseDto> getAllQuestions(int limit, int offset) {
        Query q = new Query()
                .with(Sort.by(Sort.Direction.ASC, "createdAt"))
                .skip(offset)
                .limit(limit);

        Mono<List<QuestionResponseDto>> itemsMono = template.find(q, Question.class)
                .map(QuestionMapper::toQuestionResponseDto)
                .collectList();

        Mono<Long> totalMono = template.count(new Query(), Question.class);

        return Mono.zip(itemsMono, totalMono)
                .map(t -> new QuestionListResponseDto(t.getT1(), t.getT2().intValue()));
    }

    public Mono<QuestionListResponseDto> searchQuestions(String searchTerm, int limit, int offset) {
        Mono<List<QuestionResponseDto>> question =  questionRepository.findByTitleOrContent(searchTerm, PageRequest.of(offset / limit, limit))
        .map(QuestionMapper::toQuestionResponseDto)
        .collectList(); 

        Mono<Long> totalCount = questionRepository.countByTitleContainingOrContentContaining(searchTerm);
        
        return Mono.zip(question, totalCount).map(t -> new QuestionListResponseDto(t.getT1(), t.getT2().intValue())); 
    }
}
