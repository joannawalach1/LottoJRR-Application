package com.javajuniorready.domain.numberreceiver;

import lombok.Builder;

import java.util.Set;
@Builder
public record SixNumbers(Set<Integer> userNumbers) {
}
