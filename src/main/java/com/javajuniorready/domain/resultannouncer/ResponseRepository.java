package com.javajuniorready.domain.resultannouncer;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResponseRepository extends MongoRepository<ResultResponse, String> {
    boolean existById(String hash);
    Optional<ResultResponse> findByHash(String hash);
}
