package org.work.messagingloggingservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.work.messagingloggingservice.model.LogMessage;
import org.work.messagingloggingservice.repository.LogMessageRepository;

@Service
@RequiredArgsConstructor
public class RabbitMessageConsumer {

    private final LogMessageRepository logMessageRepository;

    @RabbitListener(queues = "message_queue")
    public void consume(String message) {
        System.out.println("Consumed RabbitMQ message: " + message);
        LogMessage logMessage = new LogMessage();
        logMessage.setMessage(message);
        logMessageRepository.save(logMessage);
    }
}
