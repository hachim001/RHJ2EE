package org.example.employeeservice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "employee-events", groupId = "employee-group")
    public void consumeMessage(String message) {
        System.out.println("Message consommé depuis employee-events: " + message);
    }
}

