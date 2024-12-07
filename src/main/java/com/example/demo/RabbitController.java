package com.example.demo;

import com.example.demo.MessageProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Rabbit Controller", description = "API для работы с RabbitMQ")
@RestController
public class RabbitController {

    private final MessageProducerService messageProducerService;

    public RabbitController(MessageProducerService messageProducerService) {
        this.messageProducerService = messageProducerService;
    }

    @Operation(summary = "Отправить сообщение", description = "Отправляет сообщение в RabbitMQ")
    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        messageProducerService.sendMessage(message);
        return "Message sent: " + message;
    }
}
