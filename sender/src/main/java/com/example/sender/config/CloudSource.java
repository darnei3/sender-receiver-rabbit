package com.example.sender.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CloudSource {

    @Output("producerOutput")
    MessageChannel producerChannel();
}
