package com.example.receiver.model;


import com.example.receiver.model.MessageDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Repository {
    ArrayList<MessageDto> messageDtoRepository = new ArrayList<>();

    {
        messageDtoRepository.add(new MessageDto(0,0,"",0, ""));
    }

    public ArrayList<MessageDto> getMessageDtoRepository() {
        return messageDtoRepository;
    }
}
