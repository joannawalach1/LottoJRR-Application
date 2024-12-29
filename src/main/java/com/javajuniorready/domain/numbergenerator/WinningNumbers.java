package com.javajuniorready.domain.numbergenerator;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record WinningNumbers(int id, LocalDateTime WinningNumbersDrawDate, WinningNumbersSet winningNumbersSet) {}
