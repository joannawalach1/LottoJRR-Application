package com.javajuniorready.domain.numbergenerator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
@Document(collection = "winning_numbers")
public record WinningNumbers(
        @Id
        String id,
        LocalDateTime WinningNumbersDrawDate,
        WinningNumbersSet winningNumbersSet) {
}
