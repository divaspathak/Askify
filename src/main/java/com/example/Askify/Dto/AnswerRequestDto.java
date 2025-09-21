package com.example.Askify.Dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*; 

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerRequestDto {

    @NotBlank(message = "Content is required")
    @Size(min = 10, max = 1000, message = "Content must be between 10 and 1000 characters")
    private String content; 

    @NotBlank(message = "Question ID is required")
    private String questionId; 
}
