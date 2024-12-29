package com.javajuniorready.domain.numbergenerator;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class NumberGeneratorConfiguration {

    @Bean
    public WinningNumberGenerator winningNumberGenerator() {
        return new WinningNumberGenerator();
    }

    @Bean
    public WinningNumbersRepository winningNumbersRepository() {
        return new WinningNumbersRepository() {

            private final List<WinningNumbers> winningNumbersDatabase = new ArrayList<>();

            @Override
            public WinningNumbers saveWinningNumbers(WinningNumbers winningNumbers) {
                winningNumbersDatabase.add(winningNumbers);
                return winningNumbers;
            }

            @Override
            public List<WinningNumbers> findAll() {
                return winningNumbersDatabase;
            }

            @Override
            public Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime lottoDrawDate) {
                return Optional.ofNullable(winningNumbersDatabase.stream()
                        .filter(winningNumber -> winningNumber.WinningNumbersDrawDate().isEqual(lottoDrawDate))
                        .findFirst()
                        .orElse(null));
            }
        };
    }

    @Bean
    public NumberGeneratorFacade numberGeneratorFacade(WinningNumbersRepository winningNumbersRepository) {
        WinningNumberGenerator winningNumberGenerator = new WinningNumberGenerator();
        return new NumberGeneratorFacade(
                winningNumbersRepository
        );
    }
}
