package com.example.receiver.service;

import com.example.receiver.model.MessageDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@EnableRabbit
@Component
public class MessageReceiver {


    @RabbitListener(queues = "test-queue")
    public void receiveMessage(MessageDto message) {
        System.out.println("Received <" + message.toString() + ">");
    }

}