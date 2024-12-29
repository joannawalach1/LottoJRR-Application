package com.javajuniorready.domain.numbergenerator;

import lombok.Builder;

import java.util.Set;

@Builder
public record WinningNumbersSet(Set<Integer> winningNumbersSet) {
}
