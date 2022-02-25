package com.example.receiver.controller;


import com.example.receiver.model.Repository;
import com.example.receiver.model.MessageDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/receiver")
public class ReceiverRest {

    final Repository repository;

    public ReceiverRest(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("/messages")
    public ArrayList<MessageDto> getAllMessages(){
        return repository.getMessageDtoRepository();
    }

}
