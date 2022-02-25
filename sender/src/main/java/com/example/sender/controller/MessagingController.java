package com.example.sender.controller;


import com.example.sender.model.MessageDto;
import com.example.sender.service.CloudStreamMessageSender;
import com.example.sender.service.ThreadMessageSender;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messaging")
public class MessagingController {

    final RabbitTemplate rabbitTemplate;
    final CloudStreamMessageSender cloudStreamMessageSender;
    final ThreadMessageSender threadMessageSender;

    public MessagingController(ThreadMessageSender threadMessageSender, RabbitTemplate rabbitTemplate, CloudStreamMessageSender cloudStreamMessageSender) {
        this.threadMessageSender = threadMessageSender;
        this.rabbitTemplate = rabbitTemplate;
        this.cloudStreamMessageSender = cloudStreamMessageSender;
    }


    @GetMapping("/start")
    public void startMessaging(){
        threadMessageSender.status = true;
        threadMessageSender.start();
    }

    @GetMapping("/stop")
    public void stopMessaging(){
        threadMessageSender.status = false;
    }

    @GetMapping("/amqp/send/{message}")
    public void sendMessages(@PathVariable MessageDto message){
        rabbitTemplate.convertAndSend("client-server-exchange", "foo.bar.baz", message);
    }

    @GetMapping("/cloud/send/{message}")
    public void sendCloudMessage(@PathVariable MessageDto message){
        cloudStreamMessageSender.sendMessage(message);
    }
}
