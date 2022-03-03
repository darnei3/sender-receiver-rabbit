package com.example.sender.service;

import com.example.sender.model.MessageDto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${mes-queue-config.message.persistent}")
    private boolean isPersistentMessage;
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
