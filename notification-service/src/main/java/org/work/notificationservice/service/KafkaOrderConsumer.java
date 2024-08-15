package org.work.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.work.notificationservice.model.Notification;
import org.work.notificationservice.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class KafkaOrderConsumer {

    private final NotificationRepository notificationRepository;

    @KafkaListener(topics = "order_topic", groupId = "notification_group")
    public void consume(String message) {
        System.out.println("Received Kafka message: " + message);
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setMessage(message);
        notificationRepository.save(notification);
    }
}
