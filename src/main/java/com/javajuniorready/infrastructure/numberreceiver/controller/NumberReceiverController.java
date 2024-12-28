package com.javajuniorready.infrastructure.numberreceiver.controller;

import com.javajuniorready.domain.numberreceiver.NumberReceiverFacade;
import com.javajuniorready.domain.numberreceiver.NumberReceiverMapper;
import com.javajuniorready.domain.numberreceiver.SixNumbers;
import com.javajuniorready.domain.numberreceiver.Ticket;
import com.javajuniorready.domain.numberreceiver.dto.SixNumberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NumberReceiverController {
    private final NumberReceiverFacade numberReceiverFacade;

    @PostMapping
    public ResponseEntity<SixNumberDto> inputNumbers(@RequestBody SixNumberDto sixNumberDto) {
        SixNumbers sixNumbers = NumberReceiverMapper.toEntity(sixNumberDto);
        return ResponseEntity.ok(sixNumberDto);
    }

    @GetMapping("/ticket/{id}")
    public Ticket findTicketById(@PathVariable Integer id) {
        return numberReceiverFacade.findTicketById(id);
    }

    @GetMapping("/tickets")
    public List<Ticket> findTicketById() {
        return numberReceiverFacade.findAll();
    }

}
