package com.example.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "wap-topic", groupId = "my_group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        createFileWithContent(message, "/Users/u1061126/Documents/kafkaProducerFile.txt");
    }

    private void createFileWithContent(String content, String originalFilePath) {
        try {
            String newFilePath = originalFilePath.replace("/Documents/", "/Desktop/").replace(".txt", "-Copy.txt");
            Files.write(Paths.get(newFilePath), content.getBytes(), StandardOpenOption.CREATE);
            System.out.println("File created: " + newFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
