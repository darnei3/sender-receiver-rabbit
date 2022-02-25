package com.example.sender.model;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageDto {
    long idMs;
    long time;
    String nameSender;
    int ageSender;
    String status;

}
