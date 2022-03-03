package com.example.sender.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

    public static final String EXCHANGE_NAME = "amqpExchange";
    public static final String TOPIC_NAME = "#";
    public static final String QUEUE_NAME = "amqpQueue";



    @Bean
    public Queue myQueue(@Value("${mes-queue-config.queue.durable}") final boolean isQueueDurable){
        if (isQueueDurable){
            return new Queue(QUEUE_NAME, true);
        } else {
            return new Queue(QUEUE_NAME, false, false, true);
        }
    }


    @Bean
    public Binding binding(final Queue queue, final TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(TOPIC_NAME);
    }

    @Bean(EXCHANGE_NAME)
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}