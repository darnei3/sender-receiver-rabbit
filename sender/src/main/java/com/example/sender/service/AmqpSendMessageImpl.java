package com.example.sender.service;

import com.example.sender.config.AmqpConfig;
import com.example.sender.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@Service
@ConditionalOnProperty(value = "messenger", havingValue = "AMQP")
public class AmqpSendMessageImpl implements Messenger {

    private final RabbitTemplate rabbitTemplate;
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpSendMessageImpl.class);
    public AmqpSendMessageImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(MessageDto messageDto) {
        messageDto.setStatus("AMQP");
        rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_NAME, AmqpConfig.TOPIC_NAME, messageDto);
        LOGGER.info("AMQP send: {} messages",  messageDto.getIdMs());
    }

}
