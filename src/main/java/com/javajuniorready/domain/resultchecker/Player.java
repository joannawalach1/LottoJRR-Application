package com.javajuniorready.domain.resultchecker;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Document(collection = "player")
public record Player(
        @Id
        ObjectId hash,
        Set<Integer> userNumbers,
        Set<Integer> hitNumbers,
        LocalDateTime lottoDrawDate,
        Set<Integer> wonNumbers,
        boolean isWinner) {
}
