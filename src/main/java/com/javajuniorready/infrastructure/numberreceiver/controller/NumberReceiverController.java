package com.javajuniorready.infrastructure.numberreceiver.controller;

import com.javajuniorready.domain.numberreceiver.NumberReceiverFacade;
import com.javajuniorready.domain.numberreceiver.NumberReceiverMapper;
import com.javajuniorready.domain.numberreceiver.Ticket;
import com.javajuniorready.domain.numberreceiver.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NumberReceiverController {
    private final NumberReceiverFacade numberReceiverFacade;


    @PostMapping
    public ResponseEntity<Ticket> createTickets(@RequestBody TicketDto ticketDto) {
        Ticket ticket = NumberReceiverMapper.toTicketEntity(ticketDto);
        numberReceiverFacade.createTicket(ticketDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }


    @GetMapping("/ticket/{id}")
    public ResponseEntity<TicketDto> findTicketById(@PathVariable Integer id) {
        Ticket ticketById = numberReceiverFacade.findTicketById(id);
        TicketDto ticketDto = NumberReceiverMapper.toTicketDto(ticketById);
        return ResponseEntity.status(HttpStatus.OK).body(ticketDto);
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<Ticket>> findTicketById() {
        List<Ticket> allTickets = numberReceiverFacade.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allTickets);
    }
}
