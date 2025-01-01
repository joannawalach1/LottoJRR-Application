package com.javajuniorready.domain.resultannouncer.dto;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ResponseDto(ObjectId hash, Set<Integer> results, Set<Integer> hitNumbers, Set<Integer> wonNumbers, LocalDateTime drawDate, boolean isWinner) {
}
