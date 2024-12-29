package com.javajuniorready.domain.numberreceiver;

import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record Ticket( int id, LocalDateTime lottoDrawDate, SixNumbers sixNumbers) {
}
