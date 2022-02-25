package com.example.sender.service;

import com.example.sender.model.MessageDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MessageCreator {

    List<String> namesList = new ArrayList<>();
    {
        namesList.add("Kirill");
        namesList.add("Maxim");
        namesList.add("Anton");
        namesList.add("Anna");
        namesList.add("Katya");
        namesList.add("Alex");
    }

    public MessageDto createMessageDto(){
        Random rand = new Random();

        String name = namesList.get(rand.nextInt(namesList.size()));
        int age =  20 + (int)(Math.random()*((40 - 20) + 1));
        long currentTime = System.currentTimeMillis() / 1000L;

        return MessageDto.builder()
                .time(currentTime)
                .nameSender(name)
                .ageSender(age)
                .build();
    }



}
