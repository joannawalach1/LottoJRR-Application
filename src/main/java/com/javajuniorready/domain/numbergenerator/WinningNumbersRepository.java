package com.javajuniorready.domain.numbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WinningNumbersRepository extends MongoRepository<WinningNumbers, Integer> {
    WinningNumbers saveWinningNumbers(WinningNumbers winningNumbers);
    List<WinningNumbers> findAll();
    Optional<WinningNumbers> findWinningNumbersByWinningNumbersDrawDate(LocalDateTime lottoDrawDate);
}
