package com.egen.demo.Controller;

import com.egen.demo.Model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaController {
    @Autowired
    KafkaTemplate<String, Order> kafkaTemplate;

    private static final String TOPIC = "Kafka_Example";

    @GetMapping("/publish/{id}")
    public String post(@PathVariable("id") final String id) {

        kafkaTemplate.send(TOPIC, new Order(id, "komal","kkk@gmail.com"));

        return "Published successfully";
    }

}
