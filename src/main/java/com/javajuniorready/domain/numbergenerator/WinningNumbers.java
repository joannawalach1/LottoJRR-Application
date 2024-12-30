package com.javajuniorready.domain.numbergenerator;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
@Document(collection = "winning_numbers")
public record WinningNumbers(
        @Id
        ObjectId id,
        LocalDateTime WinningNumbersDrawDate,
        WinningNumbersSet winningNumbersSet) {}
