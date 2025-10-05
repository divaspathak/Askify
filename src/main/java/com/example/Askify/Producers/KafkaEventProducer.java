package com.example.Askify.Producers;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.Askify.Events.ViewCountEvent;
import com.example.Askify.config.KafkaConfig;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KafkaEventProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishViewCountEvent(ViewCountEvent viewCountEvent) {
        kafkaTemplate.send(KafkaConfig.TOPIC, viewCountEvent.getTargetId(), viewCountEvent)
        .whenComplete((res, error) -> {
            if (error != null) {
                System.out.println("Failed to publish event: " + error.getMessage());
            } else {
                System.out.println("Event published successfully to topic: " + res.getRecordMetadata().topic());
            }
        });
    }
}
