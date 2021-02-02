package com.egen.demo.Controller;

import com.egen.demo.Model.Order;
import com.sun.jmx.remote.util.OrderClassLoaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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

    Order orderFromTopic = null;

    private static final String TOPIC = "Kafka_Example";

    @GetMapping("/publish/{id}")
    public String post(@PathVariable("id") final String name) {

        kafkaTemplate.send("Kafka_Example_json", new Order("1", name, name+"@gmail.com"));

        return "Published successfully";
    }

    @GetMapping("/publish/orderJSON")
    public Order consumerOrderJSON() {
        System.out.println("sending json");
        return orderFromTopic;
    }

    @KafkaListener(groupId = "group_json", topics = "Kafka_Example_json", containerFactory = "orderKafkaListenerFactory")
    public Order getJSONmessage(Order order) {
        orderFromTopic = order;
        return orderFromTopic;
    }
}
