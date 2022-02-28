package com.example.sender.service;

import com.example.sender.model.MessageDto;
import org.springframework.stereotype.Component;

@Component
public interface Messenger {
    public void sendMessage(MessageDto messageDto);
}
