package com.example.receiver.service;

import com.example.receiver.model.Repository;
import com.example.receiver.model.MessageDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class AmqpReceiverService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmqpReceiverService.class);

    final Repository repository;

    public AmqpReceiverService(Repository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "test-queue")
    public void receiveMessage(MessageDto message) {
        LOGGER.info("AMQP received message: {}", message.toString());
        repository.getMessageDtoRepository().add(message);
    }

}