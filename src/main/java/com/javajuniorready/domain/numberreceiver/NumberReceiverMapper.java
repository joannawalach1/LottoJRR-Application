package com.javajuniorready.domain.numberreceiver;

import com.javajuniorready.domain.numberreceiver.dto.TicketDto;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

public class NumberReceiverMapper {

    public static Ticket toTicketEntity(TicketDto userTicket) {
        return Ticket.builder()
                .id(new ObjectId())
                .lottoDrawDate(userTicket.lottoDrawDate())
                .sixNumbers(userTicket.sixNumbers())
                .build();
    }

    public static TicketDto toTicketDto(Ticket ticket) {
        return TicketDto.builder()
                .id(ticket.id())
                .lottoDrawDate(ticket.lottoDrawDate())
                .sixNumbers(ticket.sixNumbers())
                .build();
    }

    public static List<TicketDto> toTicketDtoList(List<Ticket> tickets) {
        return tickets.stream()
                .map(NumberReceiverMapper::toTicketDto)
                .collect(Collectors.toList());
    }
}
