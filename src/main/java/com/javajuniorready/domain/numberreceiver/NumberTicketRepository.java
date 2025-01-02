package com.javajuniorready.domain.numberreceiver;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface NumberTicketRepository extends MongoRepository<Ticket, Integer> {
    Optional<Ticket> findTicketById(String id);
    Optional<Ticket> findById(String id);
};

