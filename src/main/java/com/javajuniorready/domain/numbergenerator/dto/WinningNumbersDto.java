package com.javajuniorready.domain.numbergenerator.dto;

import com.javajuniorready.domain.numbergenerator.WinningNumbersSet;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record WinningNumbersDto(int id, LocalDateTime WinningNumbersDrawDate, WinningNumbersSet winningNumbersSet) {
}
