package com.example.sender.service;

import com.example.sender.config.CloudSource;
import com.example.sender.model.MessageDto;
import lombok.AllArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CloudStreamMessageSender {

    private final CloudSource cloudSource;

    public void sendMessage(MessageDto message){
        message.setStatus("CloudStream");
        cloudSource.producerChannel().send(MessageBuilder.withPayload(message).build());
    }
}
