package com.example.demo.service;

import com.example.demo.config.RabbitMQConfig;
import com.example.demo.model.Book;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {

    private final RabbitTemplate rabbitTemplate;

    public MessageProducerService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, "routingKey", message);
        System.out.println("Message sent: " + message);
    }

    public void sendNotification(Book book) {
        String message = String.format("New book added: %s by %s", book.getTitle(), book.getAuthor());
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_NAME, message);
    }
}
