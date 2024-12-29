package com.javajuniorready.numbergenerator;

import com.javajuniorready.domain.numbergenerator.WinningNumbers;
import com.javajuniorready.domain.numbergenerator.WinningNumbersRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryWinningNumbersRepositoryImpl implements WinningNumbersRepository {
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

