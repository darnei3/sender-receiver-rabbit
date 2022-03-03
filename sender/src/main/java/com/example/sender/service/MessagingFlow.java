package com.example.sender.service;

import com.example.sender.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MessagingFlow extends Thread{

    final MessageBuilder messageBuilder;
    Messenger messenger;

    public MessagingFlow(MessageBuilder messageBuilder, Messenger messenger) {
        this.messenger = messenger;
        this.messageBuilder = messageBuilder;

    }
    public boolean status = true;


    public void run() {
        AtomicInteger atomicInt = new AtomicInteger(0);
        AtomicInteger routInt = new AtomicInteger(0);
        while (status) {
            try {
                Thread.sleep(1);
                MessageDto messageDto = messageBuilder.createMessageDto();

                messageDto.setIdMs(atomicInt.incrementAndGet());
                messageDto.setRout(routInt.getAndIncrement());

                messenger.sendMessage(messageDto);
                messenger.sendMessage(messageDto);
                messenger.sendMessage(messageDto);
                messenger.sendMessage(messageDto);
                if(routInt.get() == 3)
                    routInt.set(0);
            } catch (InterruptedException e) {
                System.out.println("Thread has been interrupted");
            }
        }
    }
}
