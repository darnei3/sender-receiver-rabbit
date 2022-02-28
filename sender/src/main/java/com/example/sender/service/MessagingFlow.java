package com.example.sender.service;

import com.example.sender.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MessagingFlow{

    final MessageBuilder messageBuilder;
    Messenger messenger;

    public MessagingFlow(MessageBuilder messageBuilder, Messenger messenger) {
        this.messenger = messenger;
        this.messageBuilder = messageBuilder;

    }
    public boolean status = true;


    public void startMessaging() {
        System.out.println("Thread has started...");
        AtomicInteger atomicInt = new AtomicInteger(0);
        while (status) {
            try {
                Thread.sleep(60);
                MessageDto messageDto = messageBuilder.createMessageDto();
                messageDto.setIdMs(atomicInt.incrementAndGet());

                messenger.sendMessage(messageDto);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
        }
    }
}
