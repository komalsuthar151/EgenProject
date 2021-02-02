package com.egen.demo.Listener;

import com.egen.demo.Model.Order;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "Kafka_Example", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }


    @KafkaListener(topics = "Kafka_Example_json", groupId = "group_json",
            containerFactory = "orderKafkaListenerFactory")
    public void consumeJson(Order order) {
        System.out.println("Consumed JSON Message: " + order);
    }
}