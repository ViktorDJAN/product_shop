package ru.kashtanov.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    //Here we have to create this method in order to use it after the program ran
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }

}
