package com.javajuniorready.domain.numbergenerator.dto;

import lombok.Builder;

import java.util.Set;
@Builder
public record WinningNumbersSetDto(Set<Integer> winningNumbersSet) {
}
