package com.javajuniorready.domain.resultchecker;

import com.javajuniorready.domain.resultchecker.dto.PlayerDto;

import java.util.List;
import java.util.stream.Collectors;

public class ResultCheckerMapper {
    public static List<Player> toPlayerEntity(List<PlayerDto> winners) {
        return winners.stream()
                .map(winner -> Player.builder()
                        .build())
                .collect(Collectors.toList());
    }

}
