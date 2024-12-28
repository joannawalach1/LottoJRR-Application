package com.javajuniorready.domain.numberreceiver.dto;

import com.javajuniorready.domain.numberreceiver.SixNumbers;
import lombok.Builder;

import java.time.LocalDateTime;
@Builder
public record TicketDto(int id, LocalDateTime lottoDrawDate, SixNumbers sixNumbers) {
}
