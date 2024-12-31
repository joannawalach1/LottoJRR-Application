package com.javajuniorready.domain.resultchecker;

import com.javajuniorready.domain.numbergenerator.NumberGeneratorFacade;
import com.javajuniorready.domain.numbergenerator.WinningNumbers;
import com.javajuniorready.domain.numbergenerator.WinningNumbersNotFound;
import com.javajuniorready.domain.numberreceiver.NumberReceiverFacade;
import com.javajuniorready.domain.numberreceiver.Ticket;
import com.javajuniorready.domain.resultchecker.dto.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
public class ResultCheckerFacade {

    private static final Logger logger = LoggerFactory.getLogger(ResultCheckerFacade.class);

    private final NumberGeneratorFacade numberGeneratorFacade;
    private final NumberReceiverFacade numberReceiverFacade;
    private final PlayerRepository playerRepository;

    public List<PlayerDto> generateResults(int ticketId, LocalDateTime drawDate) {
        logger.info("Generating results for ticket ID: {} and draw date: {}", ticketId, drawDate);
        List<PlayerDto> winners = new ArrayList<>();

        Ticket ticketById = numberReceiverFacade.findTicketById(ticketId);
        logger.info("Fetched ticket: {}", ticketById);

        WinningNumbers winningNumbersByDate = numberGeneratorFacade.findWinningNumbersByDate(drawDate)
                .orElseThrow(() -> {
                    logger.error("Winning numbers not found for draw date: {}", drawDate);
                    return new WinningNumbersNotFound("Winning numbers not found for the given draw date.");
                });
        logger.info("Fetched winning numbers for date {}: {}", drawDate, winningNumbersByDate);

        Set<Integer> winningNumbers = winningNumbersByDate.winningNumbersSet().winningNumbersSet();
        Set<Integer> userNumbers = ticketById.sixNumbers().userNumbers();
        logger.info("Winning numbers: {}, User numbers: {}", winningNumbers, userNumbers);

        Set<Integer> matchingNumbers = getMatchingNumbers(winningNumbers, userNumbers);
        logger.info("Matching numbers: {}", matchingNumbers);
        if (matchingNumbers.isEmpty()) {
            logger.error("No matching numbers found for draw date: {}", drawDate);
        }

        int matchingCount = matchingNumbers.size();
        logger.info("Number of matches: {}", matchingCount);

        if (matchingCount > 0) {
            String message = switch (matchingCount) {
                case 3 -> "Congratulations! You matched 3 numbers!";
                case 4 -> "Great job! You matched 4 numbers!";
                case 5 -> "Amazing! You matched 5 numbers!";
                case 6 -> "Jackpot! You matched all 6 numbers!";
                default -> "You matched " + matchingCount + " numbers!";
            };
            logger.info("Generated message for winner: {}", message);

            PlayerDto winner = PlayerDto.builder()
                    .results(new ArrayList<>())
                    .message(message)
                    .build();
            winners.add(winner);
            logger.info("Added winner: {}", winner);
        } else {
            logger.info("No matches found for ticket ID: {}", ticketId);
        }
;
        playerRepository.saveAll(ResultCheckerMapper.toPlayerEntity(winners));
        logger.info("Winners saved to repository: {}", winners);

        return winners;
    }

    public Set<Integer> getMatchingNumbers(Set<Integer> winningNumbers, Set<Integer> ticketNumbers) {
        Set<Integer> matchingNumbers = new HashSet<>(winningNumbers);
        matchingNumbers.retainAll(ticketNumbers);
        return matchingNumbers;
    }


}
