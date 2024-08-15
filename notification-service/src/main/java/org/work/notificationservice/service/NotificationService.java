package org.work.notificationservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.work.notificationservice.config.RabbitMQConfig;
import org.work.notificationservice.model.Notification;
import org.work.notificationservice.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private static final String LOG_TOPIC = "log_topic";
    private static final String ORDER_TOPIC = "order_topic";

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public Notification createNotification(Notification notification) {
        Notification savedNotification = notificationRepository.save(notification);
        kafkaTemplate.send(LOG_TOPIC, "Notification created: " + savedNotification.toString());
        return savedNotification;
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
        kafkaTemplate.send(LOG_TOPIC, "Notification deleted: " + id);
    }

    @KafkaListener(topics = ORDER_TOPIC, groupId = "notification_group")
    public void listenToOrderTopic(String message) {
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setMessage(message);
        Notification savedNotification = notificationRepository.save(notification);
        kafkaTemplate.send(LOG_TOPIC, "Notification created from Kafka order: " + savedNotification.toString());
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void listenToNotificationQueue(String message) {
        Notification notification = new Notification();
        notification.setUserId(1L);
        notification.setMessage(message);
        Notification savedNotification = notificationRepository.save(notification);
        kafkaTemplate.send(LOG_TOPIC, "Notification created from RabbitMQ: " + savedNotification.toString());
    }
}