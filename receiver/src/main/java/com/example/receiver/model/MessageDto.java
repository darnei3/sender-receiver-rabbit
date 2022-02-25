package com.example.receiver.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.amqp.core.Message;

import java.io.Serializable;

@Data
@Builder
public class MessageDto implements Serializable {
    long idMs;
    long time;
    String nameSender;
    int ageSender;


    public MessageDto(){

    }

    public MessageDto(long idMs, long time, String nameSender, int ageSender) {
        this.idMs = idMs;
        this.time = time;
        this.nameSender = nameSender;
        this.ageSender = ageSender;
    }
}
