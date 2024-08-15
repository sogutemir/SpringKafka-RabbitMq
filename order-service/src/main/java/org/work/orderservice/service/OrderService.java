package org.work.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.work.orderservice.config.RabbitMQConfig;
import org.work.orderservice.model.Order;
import org.work.orderservice.repository.OrderRepository;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public Order createOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        sendOrderCreatedMessage(savedOrder);
        return savedOrder;
    }

    private void sendOrderCreatedMessage(Order order) {
        String message = "Order created with ID: " + order.getId();
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
        kafkaTemplate.send("order_topic", message);
    }
}