package org.work.messagingloggingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.work.messagingloggingservice.service.KafkaMessageConsumer;
import org.work.messagingloggingservice.service.RabbitMessageConsumer;

@RestController
@RequiredArgsConstructor
public class LoggingController {

    private final KafkaMessageConsumer kafkaMessageConsumer;
    private final RabbitMessageConsumer rabbitMessageConsumer;

    @PostMapping("/log/kafka")
    public void logToKafka(@RequestBody String message) {
        kafkaMessageConsumer.consume(message);
    }

    @PostMapping("/log/rabbit")
    public void logToRabbit(@RequestBody String message) {
        rabbitMessageConsumer.consume(message);
    }
}
