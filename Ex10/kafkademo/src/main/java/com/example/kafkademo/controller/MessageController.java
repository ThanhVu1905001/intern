package com.example.kafkademo.controller;

import com.example.kafkademo.kafka.KafkaProducer;
import com.example.kafkademo.playload.Statistics;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class MessageController {

    private KafkaProducer kafkaProducer;

    public MessageController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody Statistics statistics){

        kafkaProducer.sendMessage(statistics);
        return ResponseEntity.ok("Message to kafka topic");
    }
}
