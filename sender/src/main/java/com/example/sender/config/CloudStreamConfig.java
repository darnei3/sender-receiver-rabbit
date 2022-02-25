package com.example.sender.config;


import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBinding({CloudSource.class})
public class CloudStreamConfig {
}
