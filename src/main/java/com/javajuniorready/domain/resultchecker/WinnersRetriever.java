package com.javajuniorready.domain.resultchecker;

import com.javajuniorready.domain.numberreceiver.Ticket;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
class WinnersRetriever {
    private final static int NUMBERS_WHEN_PLAYER_WON = 3;

    List<Player> retrieveWinners(List<Ticket> allTicketsByDate, Set<Integer> winningNumbers) {
        return allTicketsByDate.stream()
                .map(ticket -> {
                    Set<Integer> hitNumbers = calculateHits(winningNumbers, ticket);
                    return buildResult(ticket, hitNumbers, winningNumbers);
                })
                .toList();
    }

    private Set<Integer> calculateHits(Set<Integer> winningNumbers, Ticket ticket) {
        return ticket.sixNumbers().userNumbers().stream()
                .filter(winningNumbers::contains)
                .collect(Collectors.toSet());
    }

    private Player buildResult(Ticket ticket, Set<Integer> hitNumbers, Set<Integer> winningNumbers) {
        Player.PlayerBuilder builder = Player.builder();
        if (isWinner(hitNumbers)) {
            builder.isWinner(true);
        }
        return builder
                .hash(ticket.id())
                .userNumbers(ticket.sixNumbers().userNumbers())
                .hitNumbers(hitNumbers)
                .lottoDrawDate(ticket.lottoDrawDate())
                .wonNumbers(winningNumbers)
                .build();
    }

    private boolean isWinner(Set<Integer> hitNumbers) {
        return hitNumbers.size() >= NUMBERS_WHEN_PLAYER_WON;
    }
    }