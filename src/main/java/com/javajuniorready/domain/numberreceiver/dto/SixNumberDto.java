package com.javajuniorready.domain.numberreceiver.dto;

import lombok.Builder;

import java.util.Set;
@Builder
public record SixNumberDto(Set<Integer> userNumbers) {
}
