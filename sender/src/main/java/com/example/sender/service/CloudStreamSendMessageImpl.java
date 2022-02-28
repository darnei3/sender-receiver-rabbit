package com.example.sender.service;

import com.example.sender.model.MessageDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;




@EnableBinding(Source.class)
@Component
@ConditionalOnProperty(value = "messenger", havingValue = "CloudStream")
public class CloudStreamSendMessageImpl implements Messenger {

    final Source source;
    private static final Logger LOGGER = LoggerFactory.getLogger(CloudStreamSendMessageImpl.class);

    public CloudStreamSendMessageImpl(Source source) {
        this.source = source;
    }

    @Override
    public void sendMessage(MessageDto messageDto) {
        messageDto.setStatus("CloudStream");
        source.output().send(MessageBuilder.withPayload(messageDto).build());
        LOGGER.info("CloudStream send: {} messages",  messageDto.getIdMs());
    }
}
