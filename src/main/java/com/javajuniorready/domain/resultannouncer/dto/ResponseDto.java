package com.javajuniorready.domain.resultannouncer.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record ResponseDto(String hash, Set<Integer> results, Set<Integer> hitNumbers, Set<Integer> wonNumbers, LocalDateTime drawDate, boolean isWinner) {
}
