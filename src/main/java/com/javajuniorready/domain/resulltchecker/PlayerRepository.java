package com.javajuniorready.domain.resulltchecker;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository {
    List<Player> saveAllPlayers(List<Player> player);
    Player findByHash(String hash);
}
