package com.example.sender.service;

import com.example.sender.model.MessageDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class MessageBuilder {

    List<String> namesList = new ArrayList<>();
    {
        namesList.add("Kirill");
        namesList.add("Maxim");
        namesList.add("Anton");
        namesList.add("Anna");
        namesList.add("Katya");
        namesList.add("Alex");
    }

    List<String> messagesList = new ArrayList<>();
    {
        messagesList.add("С другой стороны рамки и место обучения кадров способствует");
        messagesList.add("Разнообразный и богатый опыт консультация с широким активом");
        messagesList.add("С другой стороны постоянное информационно-обычное");
        messagesList.add("Если у вас есть какие то интересные предложения, обращайтесь!");
        messagesList.add("Повседневная практика показывает, что реализация намеченных плановых заданий");
        messagesList.add("Товарищи! сложившаяся структура организации представляет");
    }

    public MessageDto createMessageDto(){
        Random rand = new Random();

        String name = namesList.get(rand.nextInt(namesList.size()));
        String mainMessage = messagesList.get(rand.nextInt(messagesList.size()));
        int age =  20 + (int)(Math.random()*((40 - 20) + 1));
        long currentTime = System.currentTimeMillis() / 1000L;

        return MessageDto.builder()
                .time(currentTime)
                .nameSender(name)
                .ageSender(age)
                .mainMessage(mainMessage)
                .build();
    }



}
