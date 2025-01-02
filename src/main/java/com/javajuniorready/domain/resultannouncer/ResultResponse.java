package com.javajuniorready.domain.resultannouncer;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Document
public record ResultResponse(
        @Id
        String hash,
        Set<Integer> results,
        Set<Integer> hitNumbers,
        Set<Integer> wonNumbers,
        LocalDateTime drawDate,
        boolean isWinner,
        @Indexed(expireAfterSeconds = 10)
        LocalDateTime createdDate) {
}
