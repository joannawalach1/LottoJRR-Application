package com.javajuniorready.domain.numberreceiver;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "user_tickets")
@Builder
public record Ticket(
        @Id
        int id,
        LocalDateTime lottoDrawDate,
        SixNumbers sixNumbers) {
}
