package com.example.sender.controller;


import com.example.sender.model.MessageDto;
import com.example.sender.service.CloudStreamSendMessageImpl;
import com.example.sender.service.MessagingFlow;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messaging")
public class MessagingController {

    final MessagingFlow messagingFlow;

    public MessagingController(MessagingFlow messagingFlow) {
        this.messagingFlow = messagingFlow;
    }


    @GetMapping("/start")
    public void startMessaging(){
        messagingFlow.status = true;
        messagingFlow.start();
    }

    @GetMapping("/stop")
    public void stopMessaging(){
        messagingFlow.status = false;
    }

//    @GetMapping("/amqp/send/{message}")
//    public void sendMessages(@PathVariable MessageDto message){
//        rabbitTemplate.convertAndSend("client-server-exchange", "foo.bar.baz", message);
//    }
//
//    @GetMapping("/cloud/send/")
//    public void sendCloudMessage(){
//        MessageDto messageDto = MessageDto.builder()
//                .ageSender(1)
//                .nameSender("1")
//                .time(1)
//                .idMs(1)
//                .status("1").build();
//        cloudStreamSendMessageImpl.sendMessage(messageDto);
//    }
}
