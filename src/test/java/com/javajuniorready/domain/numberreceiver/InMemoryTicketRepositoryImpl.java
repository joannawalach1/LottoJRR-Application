package com.javajuniorready.domain.numberreceiver;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryTicketRepositoryImpl implements NumberTicketRepository {

    private final List<Ticket> ticketsDatabase = new ArrayList<>();

    @Override
    public Ticket saveTickets(Ticket ticket) {
        ticketsDatabase.add(ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> findTicketById(Integer id) {
        return Optional.ofNullable(ticketsDatabase.stream()
                .filter(ticket -> ticket.id() == (id))
                .findFirst()
                .orElse(null));
    }

    @Override
    public List<Ticket> findAll() {
        return ticketsDatabase;
    }
};
