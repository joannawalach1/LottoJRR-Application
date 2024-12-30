package com.javajuniorready.domain.numbergenerator;

import com.javajuniorready.domain.numberreceiver.NumberReceiverFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class NumberGeneratorConfiguration {

    @Bean
    public WinningNumberGenerator winningNumberGenerator() {
        return new WinningNumberGenerator();
    }

    @Bean
    public NumberGeneratorFacade numberGeneratorFacade(WinningNumbersRepository winningNumbersRepository, NumberReceiverFacade numberReceiverFacade) {
        WinningNumberGenerator winningNumberGenerator = new WinningNumberGenerator();
        return new NumberGeneratorFacade(
                winningNumberGenerator,
                winningNumbersRepository,
                numberReceiverFacade
        );
    }
}
