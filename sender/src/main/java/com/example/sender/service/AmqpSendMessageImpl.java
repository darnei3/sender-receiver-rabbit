package com.example.sender.service;

import com.example.sender.config.AmqpConfig;
import com.example.sender.model.MessageDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;


@Service
@ConditionalOnProperty(value = "messenger", havingValue = "AMQP")
public class AmqpSendMessageImpl implements Messenger {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpSendMessageImpl.class);
    @Value("${mes-queue-config.message.persistent}")
    private boolean isPersistentMessage;
    public AmqpSendMessageImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendMessage(MessageDto messageDto) {


        String routingKey = String.valueOf(messageDto.getRout());

        messageDto.setStatus("AMQP");
        String s = null;
        try {
            s = mapper.writeValueAsString(messageDto);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        Message message;

        if(!isPersistentMessage) {
            message = MessageBuilder.withBody(s.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON).setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT)
                    .build();
        } else {
            message = MessageBuilder.withBody(s.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON).setDeliveryMode(MessageDeliveryMode.PERSISTENT)
                    .build();
        }



//        rabbitTemplate.convertAndSend(AmqpConfig.EXCHANGE_NAME, AmqpConfig.TOPIC_NAME, message);
        rabbitTemplate.convertAndSend("testExchange", routingKey, message);
        LOGGER.info("AMQP send: {} messages and rout: {}",  messageDto.getIdMs(), messageDto.getRout());
    }

}
