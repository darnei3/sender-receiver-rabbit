package com.example.receiver.service;


import com.example.receiver.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@EnableBinding(Sink.class)
@Component
@ConditionalOnProperty(value = "messenger.receiver.cloudStream", havingValue = "CloudStream")
public class CloudStreamReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudStreamReceiver.class);

    @StreamListener(target = Sink.INPUT)
    public void handleMessage(MessageDto message){
        LOGGER.info("CloudStream received new message: {}", message.toString());
    }
}
