package com.javajuniorready.domain.numberreceiver.dto;

import com.javajuniorready.domain.numberreceiver.SixNumbers;
import lombok.Builder;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
@Builder
public record TicketDto(ObjectId id, LocalDateTime lottoDrawDate, SixNumbers sixNumbers) {
}
