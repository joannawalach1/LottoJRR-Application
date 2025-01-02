package com.javajuniorready.domain.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NumberReceiverConfiguration {
    private NumberReceiverFacadeConfigurationProperties properties;

    @Bean
    public NumberReceiverFacade numberReceiverFacade(NumberTicketRepository numberTicketRepository) {
        NumbersValidator validator = new NumbersValidator(properties);
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
