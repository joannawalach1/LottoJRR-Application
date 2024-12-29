package com.javajuniorready.domain.numberreceiver;

import java.util.List;
import java.util.Optional;

public interface NumberTicketRepository {
    Ticket saveTickets(Ticket ticket);
    Optional<Ticket> findTicketById(Integer id);
    List<Ticket> findAll();
}
