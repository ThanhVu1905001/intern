package com.example.rabbitmq.controller;

import com.example.rabbitmq.producer.RabbitMQProducer;
import com.example.rabbitmq.model.ProductStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
    private RabbitMQProducer producer;

    @Autowired
    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    @PostMapping("publish")
    public ResponseEntity<String> sendMessage(@RequestBody ProductStatistics productStatistics){
        producer.sendMessage(productStatistics);
        return ResponseEntity.ok("Received JSON: " + productStatistics.toString());
    }
}
