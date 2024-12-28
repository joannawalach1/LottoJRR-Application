package com.javajuniorready.domain.numberreceiver;

import com.javajuniorready.domain.numberreceiver.dto.SixNumberDto;
import com.javajuniorready.domain.numberreceiver.dto.TicketDto;

public class NumberReceiverMapper {
    public static SixNumbers toEntity(SixNumberDto sixNumberDto) {
        return SixNumbers.builder()
                .userNumbers(sixNumberDto.userNumbers())
                .build();
    }

    public static SixNumberDto toDto(SixNumbers sixNumbers) {
        return SixNumberDto.builder()
                .userNumbers(sixNumbers.userNumbers())
                .build();
    }

    public static Ticket toTicketEntity(TicketDto userTicket) {
        return Ticket.builder()
                .lottoDrawDate(userTicket.lottoDrawDate())
                .sixNumbers(userTicket.sixNumbers())
                .build();
    }

    public static TicketDto toTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .lottoDrawDate(ticket.lottoDrawDate())
                .sixNumbers(ticket.sixNumbers())
                .build();
    }
}
