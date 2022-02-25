package com.example.sender.service;

import com.example.sender.model.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Service
public class AmqpMessageSender {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    private final FanoutExchange exchange;
    public AmqpMessageSender(RabbitTemplate rabbitTemplate, ObjectMapper mapper, FanoutExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
        this.exchange = exchange;
    }

    public void sendMessage(MessageDto message) {
        message.setStatus("Amqp");
        rabbitTemplate.convertAndSend("new-fanout-exchange", "foo.bar.baz", message);
    }
}
