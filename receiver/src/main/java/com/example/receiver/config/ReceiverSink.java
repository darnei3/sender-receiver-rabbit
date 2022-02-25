package com.example.receiver.config;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ReceiverSink {

    @Input("ProducerInput")
    SubscribableChannel receiveChannel();
}
