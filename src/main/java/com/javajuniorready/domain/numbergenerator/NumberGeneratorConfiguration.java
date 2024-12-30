package com.javajuniorready.domain.numbergenerator;

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
    public NumberGeneratorFacade numberGeneratorFacade(WinningNumbersRepository customWinningNumbersRepository) {
        WinningNumberGenerator winningNumberGenerator = new WinningNumberGenerator();
        return new NumberGeneratorFacade(
                customWinningNumbersRepository
        );
    }
}
