package com.example.Askify.Model;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "answers")

public class Answer {
    @Id
    private String id; 

    @Indexed(unique = true)
    private String questionId; 

    @NotBlank(message = "Answer is required")
    @Size(min = 10, max = 1000, message = "Answer must be between 10 and 1000 characters")
    private String content; 

    private String authorId;

    @CreatedDate
    private LocalDateTime createdAt; 

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
