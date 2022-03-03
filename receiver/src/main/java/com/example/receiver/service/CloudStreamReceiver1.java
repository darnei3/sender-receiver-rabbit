package com.example.receiver.service;


import com.example.receiver.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(value = "messenger.receiver.cloudStream1", havingValue = "CloudStream1")
public class CloudStreamReceiver1 {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudStreamReceiver1.class);

    @StreamListener("input1")
    public void handleMessage(MessageDto message){
        LOGGER.info("CloudStream1 received new message: {}", message.toString());
    }
}
