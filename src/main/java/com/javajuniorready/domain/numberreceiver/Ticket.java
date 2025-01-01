package com.javajuniorready.domain.numberreceiver;

import lombok.Builder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "user_tickets")
@Builder
public record Ticket(
        @Id
        ObjectId id,
        LocalDateTime lottoDrawDate,
        SixNumbers sixNumbers) {
}
