package com.example.receiver.service;


import com.example.receiver.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class CloudStreamReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CloudStreamReceiverService.class);

    @StreamListener("ProducerInput")
    public void handleMessage(MessageDto message){
        LOGGER.info("CloudStream received new message: {}", message.toString());
    }
}
