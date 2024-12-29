package com.javajuniorready.domain.numberreceiver;

import java.util.List;

public interface NumberReceiverRepository {
Ticket createTicket(Ticket ticket);
List<Ticket> findAll();
Ticket findById(int id);
}
