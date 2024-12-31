package com.javajuniorready.domain.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberReceiverConfiguration {

    @Bean
    public NumberReceiverFacade numberReceiverFacade(NumberTicketRepository numberTicketRepository) {
        NumbersValidator validator = new NumbersValidator();
        NumberReceiverMapper numberReceiverMapper = new NumberReceiverMapper();
        LottoDrawDateGenerator lottoDrawDateGenerator = new LottoDrawDateGenerator();
        return new NumberReceiverFacade(
                validator,
                numberReceiverMapper,
                numberTicketRepository,
                lottoDrawDateGenerator
        );
    }
}
