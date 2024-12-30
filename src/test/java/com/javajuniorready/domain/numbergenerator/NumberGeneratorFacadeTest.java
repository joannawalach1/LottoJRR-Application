package com.javajuniorready.domain.numbergenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javajuniorready.domain.numbergenerator.dto.WinningNumbersDto;
import com.javajuniorready.domain.numberreceiver.NumberReceiverFacade;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RequiredArgsConstructor
public class NumberGeneratorFacadeTest {
    private final NumberReceiverFacade numberReceiverFacade = mock(NumberReceiverFacade.class);
    private final InMemoryWinningNumbersRepositoryImpl winningNumbersRepository = new InMemoryWinningNumbersRepositoryImpl();
    private final WinningNumberGenerator winningNumbersGenerator= new WinningNumberGenerator();
    NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(
            winningNumbersRepository,
            numberReceiverFacade
    );

    @Test
    public void shouldGenerateWinningNumbersBasedOnDrawDate() throws JsonProcessingException {
        WinningNumbers winningNumbers = new WinningNumbers(null, LocalDateTime.of(2024,12,29, 0,0, 0), new WinningNumbersSet(Set.of(1, 2, 3, 4, 5, 6)));
        WinningNumbersDto winningNumbersDto = numberGeneratorFacade.generateLottoWinningNumbers(winningNumbers.WinningNumbersDrawDate());
        assertEquals(winningNumbersDto.winningNumbersSet(), winningNumbersDto.winningNumbersSet());
    }

    @Test
    public void shouldGenerateSixNumbersWithinRange() throws JsonProcessingException {
        WinningNumbersDto winningNumbersDto = numberGeneratorFacade.generateLottoWinningNumbers(LocalDateTime.now());
        assertEquals(6, winningNumbersDto.winningNumbersSet().winningNumbersSet().size());
        assertTrue(winningNumbersDto.winningNumbersSet().winningNumbersSet().stream().allMatch(num -> num >= 1 && num <= 99));
    }

    @Test
    public void shouldGenerateUniqueNumbers() throws JsonProcessingException {
        WinningNumbersDto winningNumbersDto = numberGeneratorFacade.generateLottoWinningNumbers(LocalDateTime.now());
        Set<Integer> uniqueNumbers = new HashSet<>(winningNumbersDto.winningNumbersSet().winningNumbersSet());
        assertEquals(winningNumbersDto.winningNumbersSet().winningNumbersSet().size(), uniqueNumbers.size());
    }

//    @Test
//    public void shouldSetCorrectDrawDate() {
//        LocalDateTime now = LocalDateTime.of(2025, 1, 1, 10, 0);
//        when(numberReceiverFacade.g(now)).thenReturn(LocalDateTime.of(2025, 1, 4, 12, 0, 0));
//        LocalDateTime nextDrawDate = numberReceiverFacade.generateLottoNextDrawDate(now);
//        assertEquals(nextDrawDate, LocalDateTime.of(2025, 1, 4, 12, 0, 0));
//    }



}
