package com.javajuniorready.domain.numbergenerator.dto;

import com.javajuniorready.domain.numbergenerator.WinningNumbersSet;
import lombok.Builder;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
@Builder
public record WinningNumbersDto(ObjectId id, LocalDateTime WinningNumbersDrawDate, WinningNumbersSet winningNumbersSet) {
}
