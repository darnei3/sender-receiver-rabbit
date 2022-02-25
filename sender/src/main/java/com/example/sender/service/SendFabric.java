package com.example.sender.service;

import com.example.sender.model.MessageDto;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class SendFabric extends Thread{

    final MessageCreator messageCreator;
    final ConvAndSend convAndSend;
    public SendFabric(MessageCreator messageCreator, ConvAndSend convAndSend) {
        this.messageCreator = messageCreator;
        this.convAndSend = convAndSend;
    }
    public boolean status = true;


    @Override
    public void run() {
        System.out.println("Thread has started...");
        AtomicInteger atomicInt = new AtomicInteger(0);
        while (status) {
            try {
                Thread.sleep(60);
                MessageDto messageDto = messageCreator.createMessageDto();
                messageDto.setIdMs(atomicInt.incrementAndGet());
                System.out.println(messageDto.toString());
                convAndSend.sendMessage(messageDto);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
        }
    }
}
