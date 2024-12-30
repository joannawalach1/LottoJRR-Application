package com.javajuniorready.domain.resultchecker.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
@Builder
public record ResultDto(String hash, Set<Integer> userNumbers, Set<Integer> hitNumbers, LocalDateTime lottoDrawDate, boolean isWinner) {
}
