package com.example.sender.service;

import com.example.sender.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ThreadMessageSender extends Thread{

    private static final Logger LOGGER = LoggerFactory.getLogger(ThreadMessageSender.class);

    final MessageBuilder messageBuilder;
    final AmqpMessageSender amqpMessageSender;
    final CloudStreamMessageSender cloudStreamMessageSender;
    public ThreadMessageSender(MessageBuilder messageBuilder, AmqpMessageSender amqpMessageSender, CloudStreamMessageSender cloudStreamMessageSender) {
        this.messageBuilder = messageBuilder;
        this.amqpMessageSender = amqpMessageSender;
        this.cloudStreamMessageSender = cloudStreamMessageSender;
    }
    public boolean status = true;


    @Override
    public void run() {
        System.out.println("Thread has started...");
        AtomicInteger atomicInt = new AtomicInteger(0);
        while (status) {
            try {
                Thread.sleep(60);
                MessageDto messageDto = messageBuilder.createMessageDto();
                messageDto.setIdMs(atomicInt.incrementAndGet());

                amqpMessageSender.sendMessage(messageDto);
                cloudStreamMessageSender.sendMessage(messageDto);

                LOGGER.info("CloudStream send: {} and AMQP send: {} messages", atomicInt.toString(),  atomicInt.toString());
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
        }
    }
}
