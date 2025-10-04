package com.example.Askify.Controllers;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Askify.Dto.QuestionListResponseDto;
import com.example.Askify.Dto.QuestionRequestDto;
import com.example.Askify.Dto.QuestionResponseDto;
import com.example.Askify.Services.IQuestionService;
import com.example.Askify.Services.QuestionService;
import reactor.core.publisher.Mono;

@Component
@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final IQuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping()
    public Mono<QuestionListResponseDto> getAllQuestions(
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset) {
        return questionService.getAllQuestions(limit, offset);
    }

    @PostMapping("/create")
    public Mono<QuestionResponseDto> createQuestion(@RequestBody QuestionRequestDto questionResquestDto) {
        return questionService.createQuestion(questionResquestDto);
    }

    @GetMapping("/{id}")
    public Mono<QuestionResponseDto> getQuestionById(@PathVariable String id) {
        return questionService.getQuestionById(id); 
    }

    @GetMapping("/search")
    public Mono<QuestionListResponseDto> searchQuestions(
            @RequestParam(name = "searchTerm") String searchTerm,
            @RequestParam(name = "limit", defaultValue = "10") int limit,
            @RequestParam(name = "offset", defaultValue = "0") int offset) {

        return questionService.searchQuestions(searchTerm, limit, offset); 
    }
}
