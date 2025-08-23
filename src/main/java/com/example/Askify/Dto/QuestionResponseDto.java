package com.example.Askify.Dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class QuestionResponseDto {
    private String id;
    private String title; 
    private String content; 
    private LocalDateTime createdAt; 
}
