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
    String status;
    String mainMessage;
    int rout;


    public MessageDto(){

    }

    public MessageDto(long idMs, long time, String nameSender, int ageSender, String status, String mainMessage, int rout) {
        this.idMs = idMs;
        this.time = time;
        this.nameSender = nameSender;
        this.ageSender = ageSender;
        this.status = status;
        this.mainMessage = mainMessage;
        this.rout = rout;
    }
}
