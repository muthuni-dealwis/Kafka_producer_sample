package com.example.producer.service;


import com.example.schema.Message;
import org.apache.avro.Protocol;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, Message> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, Message> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

//    public void sendMessage(String message){
//        kafkaTemplate.send(topicName, message);
//    }

    public void sendMessage(Message message) {
        ProducerRecord<String, Message> record = new ProducerRecord<>(topicName, String.valueOf(message.getId()), message);
        kafkaTemplate.send(record);
        System.out.println("Sent Avro User: " + message);
    }
}
