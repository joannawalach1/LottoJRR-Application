package com.javajuniorready.domain.resultchecker;

import com.javajuniorready.domain.numbergenerator.NumberGeneratorFacade;
import com.javajuniorready.domain.numbergenerator.WinningNumbers;
import com.javajuniorready.domain.numbergenerator.WinningNumbersNotFound;
import com.javajuniorready.domain.numbergenerator.WinningNumbersSet;
import com.javajuniorready.domain.numberreceiver.NumberReceiverFacade;
import com.javajuniorready.domain.numberreceiver.SixNumbers;
import com.javajuniorready.domain.numberreceiver.Ticket;
import com.javajuniorready.domain.resultchecker.dto.PlayerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ResultCheckerFacadeTest {
    private final NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
    private final NumberGeneratorFacade numberGeneratorFacade = mock(NumberGeneratorFacade.class);
    private final InMemoryPlayerRepositoryImpl inMemoryPlayerRepository = new InMemoryPlayerRepositoryImpl();
    private ResultCheckerFacade resultCheckerFacade;

    @BeforeEach
    void setUp() {
        resultCheckerFacade = new ResultCheckerFacade(
                numberGeneratorFacade,
                numberReceiverFacade,
                inMemoryPlayerRepository,
                new WinnersRetriever()
        );
    }

    @Test
    void shouldReturnWinnerWhenMatchingNumbersFound() {
        // given
        int ticketId = 1;
        LocalDateTime drawDate = LocalDateTime.now();
        Ticket ticket = new Ticket("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6)));
        WinningNumbers winningNumbers = new WinningNumbers("1", LocalDateTime.of(2024,12,29, 0,0, 0), new WinningNumbersSet(Set.of(1, 2, 3, 4, 5, 6)));

        when(numberReceiverFacade.findTicketById(ticketId)).thenReturn(ticket);
        when(numberGeneratorFacade.findWinningNumbersByDate(drawDate)).thenReturn(Optional.of(winningNumbers));

        // when
        List<PlayerDto> results = resultCheckerFacade.generateResults(ticketId, drawDate);

        // then
        assertEquals(1, results.size());
        assertEquals("Jackpot! You matched all 6 numbers!", results.get(0).message());
        verify(numberReceiverFacade, times(1)).findTicketById(ticketId);
        verify(numberGeneratorFacade, times(1)).findWinningNumbersByDate(drawDate);
    }

    @Test
    void shouldThrowWinningNumbersNotFoundWhenNoWinningNumbersForDrawDate() {
        // given
        int ticketId = 1;
        LocalDateTime drawDate = LocalDateTime.now();
        when(numberReceiverFacade.findTicketById(ticketId)).thenReturn(new Ticket("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6))));
        when(numberGeneratorFacade.findWinningNumbersByDate(drawDate)).thenReturn(Optional.empty());

        // when + then
        assertThrows(WinningNumbersNotFound.class, () -> {
            resultCheckerFacade.generateResults(ticketId, drawDate);
        });
        verify(numberReceiverFacade, times(1)).findTicketById(ticketId);
        verify(numberGeneratorFacade, times(1)).findWinningNumbersByDate(drawDate);

    }

    @Test
    void shouldReturnEmptyResultsWhenNoMatchingNumbers() {
        int ticketId = 3;
        LocalDateTime drawDate = LocalDateTime.now();

        Ticket ticket = new Ticket("1", LocalDateTime.now(), new SixNumbers(Set.of(7, 8, 9, 10, 11, 12)));
        WinningNumbers winningNumbers = new WinningNumbers(null, LocalDateTime.of(2024,12,29, 0,0, 0), new WinningNumbersSet(Set.of(1, 2, 3, 4, 5, 6)));

        when(numberReceiverFacade.findTicketById(ticketId)).thenReturn(ticket);
        when(numberGeneratorFacade.findWinningNumbersByDate(drawDate)).thenReturn(Optional.of(winningNumbers));

        List<PlayerDto> results = resultCheckerFacade.generateResults(ticketId, drawDate);
        assertEquals(0, results.size());
        verify(numberReceiverFacade, times(1)).findTicketById(ticketId);
        verify(numberGeneratorFacade, times(1)).findWinningNumbersByDate(drawDate);
    }

}
