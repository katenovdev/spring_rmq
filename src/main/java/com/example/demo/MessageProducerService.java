package com.example.demo;

import com.example.demo.config.RabbitMQConfig;
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
}
