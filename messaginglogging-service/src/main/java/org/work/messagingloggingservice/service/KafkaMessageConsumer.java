package org.work.messagingloggingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.work.messagingloggingservice.model.LogMessage;
import org.work.messagingloggingservice.repository.LogMessageRepository;

@Service
@RequiredArgsConstructor
public class KafkaMessageConsumer {

    private final LogMessageRepository logMessageRepository;

    @KafkaListener(topics = "log_topic", groupId = "log-group")
    public void consume(String message) {
        System.out.println("Consumed Kafka message: " + message);
        LogMessage logMessage = new LogMessage();
        logMessage.setMessage(message);
        logMessageRepository.save(logMessage);
    }
}
