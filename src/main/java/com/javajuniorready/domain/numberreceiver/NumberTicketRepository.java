package com.javajuniorready.domain.numberreceiver;

import java.util.List;

public interface NumberTicketRepository {
    Ticket saveTickets(Ticket ticketEntity);
    Ticket findTicketById(Integer id);
    List<Ticket> findAll();
}
