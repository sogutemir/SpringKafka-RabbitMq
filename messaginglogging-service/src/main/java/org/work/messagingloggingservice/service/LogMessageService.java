package org.work.messagingloggingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.work.messagingloggingservice.model.LogMessage;
import org.work.messagingloggingservice.repository.LogMessageRepository;

@Service
@RequiredArgsConstructor
public class LogMessageService {

    private final LogMessageRepository logMessageRepository;

    @KafkaListener(topics = "log_topic", groupId = "log-group")
    public void consumeKafkaMessage(String message) {
        System.out.println("Consumed log message from Kafka: " + message);
        LogMessage logMessage = new LogMessage();
        logMessage.setMessage(message);
        logMessageRepository.save(logMessage);
    }

    @RabbitListener(queues = "notification_queue")
    public void consumeRabbitMessage(String message) {
        System.out.println("Consumed log message from RabbitMQ: " + message);
        LogMessage logMessage = new LogMessage();
        logMessage.setMessage(message);
        logMessageRepository.save(logMessage);
    }
}