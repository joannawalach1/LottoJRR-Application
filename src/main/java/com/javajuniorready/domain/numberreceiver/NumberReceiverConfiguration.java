package com.javajuniorready.domain.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberReceiverConfiguration {

    @Bean
    public NumberReceiverFacade numberReceiverFacade() {
        return new NumberReceiverFacade();
    }
}
