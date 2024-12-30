package com.javajuniorready.domain.resulltchecker;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record Player(String hash, Set<Integer> userNumbers, Set<Integer> hitNumbers, LocalDateTime lottoDrawDate, boolean isWinner) {
}
