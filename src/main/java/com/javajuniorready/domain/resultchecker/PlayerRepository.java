package com.javajuniorready.domain.resultchecker;

import com.javajuniorready.domain.resultchecker.dto.ResultDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository<P, I extends Number> extends MongoRepository<Player, Integer> {
       Optional<Player> findById(String ticketId);
}
