package org.work.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.work.notificationservice.model.Notification;
import org.work.notificationservice.repository.NotificationRepository;

@Service
@RequiredArgsConstructor
public class RabbitOrderConsumer {

    private final NotificationRepository notificationRepository;

    @RabbitListener(queues = "order_queue")
    public void consume(String message) {
        System.out.println("Received RabbitMQ message: " + message);
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setMessage(message);
        notificationRepository.save(notification);
    }
}
