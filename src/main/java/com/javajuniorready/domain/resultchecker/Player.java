package com.javajuniorready.domain.resultchecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record Player(String hash, Set<Integer> userNumbers, Set<Integer> hitNumbers, LocalDateTime lottoDrawDate, Set<Integer> wonNumbers, boolean isWinner) {
}
