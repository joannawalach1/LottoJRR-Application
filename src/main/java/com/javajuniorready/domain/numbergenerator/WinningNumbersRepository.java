package com.javajuniorready.domain.numbergenerator;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface WinningNumbersRepository extends MongoRepository<WinningNumbers, Integer> {
    Optional<WinningNumbers> findWinningNumbersByWinningNumbersDrawDate(LocalDateTime lottoDrawDate);
}
