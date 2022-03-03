package com.example.receiver.service;

import com.example.receiver.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@ConditionalOnProperty(value = "messenger.receiver.amqp", havingValue = "AMQP")
public class AmqpReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpReceiver.class);

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "testQueue"), exchange = @Exchange(value = "testExchange", type = "topic", durable = "true")))
    public void receiveMessage(MessageDto message) {

        LOGGER.info("AMQP received message: {}", message.toString());
    }

}