package com.example.sender.controller;


import com.example.sender.service.SendFabric;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/control")
public class ThreadControl {

    final RabbitTemplate rabbitTemplate;

    final SendFabric sendFabric;

    public ThreadControl(SendFabric sendFabric, RabbitTemplate rabbitTemplate) {
        this.sendFabric = sendFabric;
        this.rabbitTemplate = rabbitTemplate;
    }


    @GetMapping("/start")
    public void startMessaging(){
        sendFabric.status = true;
        sendFabric.start();
    }

    @GetMapping("/stop")
    public void stopMessaging(){
        sendFabric.status = false;
    }

    @GetMapping("/send")
    public void sendMessages(){
        rabbitTemplate.convertAndSend("client-server-exchange", "foo.bar.baz", "Сообщение от клиента!");
    }
}
