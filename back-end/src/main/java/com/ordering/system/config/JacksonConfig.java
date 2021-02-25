package com.ordering.system.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ordering.system.domains.PaymentCard;
import com.ordering.system.domains.PaymentTicket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
            public void configure(ObjectMapper objectMapper) {
                objectMapper.registerSubtypes(PaymentCard.class);
                objectMapper.registerSubtypes(PaymentTicket.class);
                super.configure(objectMapper);
            }
        };
        return builder;
    }
}
