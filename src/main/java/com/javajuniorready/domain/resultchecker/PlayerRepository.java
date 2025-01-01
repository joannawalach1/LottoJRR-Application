package com.javajuniorready.domain.resultchecker;

import com.javajuniorready.domain.resultchecker.dto.ResultDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository<P, I extends Number> extends MongoRepository<Player, Integer> {
    ResultDto findById(String id);
}
