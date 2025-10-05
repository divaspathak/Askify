package com.example.Askify.Consumers;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.Askify.Events.ViewCountEvent;
import com.example.Askify.Repository.QuestionRepository;
import com.example.Askify.config.KafkaConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaEventConsumer {
    private final QuestionRepository questionRepository;

    @KafkaListener(topics = KafkaConfig.TOPIC, groupId = "view-count-consumer", containerFactory = "kafkaListenerContainerFactory")
    public void handleViewCount(ViewCountEvent viewCountEvent) {
        questionRepository.findById(viewCountEvent.getTargetId())
                .flatMap(question -> {
                    question.setViews(question.getViews() == null ? 1 : question.getViews() + 1);
                    return questionRepository.save(question);
                })
                .subscribe(updatedQuestion -> {
                    System.out.println("Updated view count for question ID: " + updatedQuestion.getId() + " to "
                            + updatedQuestion.getViews());
                }, error -> {
                    System.err.println("Error updating view count: " + error.getMessage());
                });
    }
}
