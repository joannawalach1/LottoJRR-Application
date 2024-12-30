package com.javajuniorready.domain.numberreceiver;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NumberTicketRepository {
    Ticket saveTickets(Ticket ticket);
    Optional<Ticket> findTicketById(Integer id);
    List<Ticket> findAll();
}
