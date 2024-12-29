package com.javajuniorready.domain.numbergenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface WinningNumbersRepository {
    WinningNumbers saveWinningNumbers(WinningNumbers winningNumbers);
    List<WinningNumbers> findAll();
    Optional<WinningNumbers> findWinningNumbersByDrawDate(LocalDateTime lottoDrawDate);
}
