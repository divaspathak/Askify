package com.example.Askify.Dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class QuestionListResponseDto {
    private List<QuestionResponseDto> questions;
    private long totalCount;
}
