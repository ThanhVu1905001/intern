package com.example.kafkademo.kafka;

import com.example.kafkademo.playload.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static  final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);

    private KafkaTemplate<String, Statistics> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Statistics> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Statistics statistics){

        LOGGER.info(String.format("Message sent--> %s", statistics.toString()));

        Message<Statistics> message = MessageBuilder
                .withPayload(statistics)
                .setHeader(KafkaHeaders.TOPIC, "statistics")
                .build();

        kafkaTemplate.send(message);
    }
}
