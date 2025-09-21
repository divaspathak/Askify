package com.example.Askify.Model;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.Askify.Enums.TargetType;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "likes")
public class Like {

    @Id
    private String id; 

    private String targetId;

    private TargetType targetType; 

    private String userId;

    private Boolean isLike; 

    @CreatedDate
    private LocalDateTime createdAt;

}
