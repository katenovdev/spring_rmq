package com.example.demo.service;

import com.example.demo.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void sendNotification(String message) {
        System.out.println("Sending notification to users: " + message);
    }
}