package com.javajuniorready.numbergenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javajuniorready.domain.numbergenerator.*;
import com.javajuniorready.domain.numbergenerator.dto.WinningNumbersDto;
import com.javajuniorready.infrastructure.numbergenerator.http.WinningNumbersFetcher;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class NumberGeneratorFacadeTest {
    private final WinningNumberGenerator winningNumberGenerator = new WinningNumberGenerator();
    private final InMemoryWinningNumbersRepositoryImpl winningNumbersRepository = new InMemoryWinningNumbersRepositoryImpl();
    NumberGeneratorFacade numberGeneratorFacade = new NumberGeneratorFacade(
            winningNumbersRepository
    );

    @Test
    public void shouldGenerateWinningNumbersBasedOnDrawDate() throws JsonProcessingException {
        WinningNumbers winningNumbers = new WinningNumbers(1, LocalDateTime.of(2024,12,29, 0,0, 0), new WinningNumbersSet(Set.of(1, 2, 3, 4, 5, 6)));
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

    @Test
    public void shouldSetCorrectDrawDate() throws JsonProcessingException {
        LocalDateTime expectedDate = LocalDateTime.of(2024, 12, 29, 12, 0, 0);
        WinningNumbersDto winningNumbersDto = numberGeneratorFacade.generateLottoWinningNumbers(expectedDate);
        assertEquals(expectedDate, winningNumbersDto.WinningNumbersDrawDate());
    }

    @Test
    public void shouldGenerateUniqueAndSequentialIds() throws JsonProcessingException {
        WinningNumbersDto first = numberGeneratorFacade.generateLottoWinningNumbers(LocalDateTime.now());
        WinningNumbersDto second = numberGeneratorFacade.generateLottoWinningNumbers(LocalDateTime.now().plusDays(7));
        assertNotEquals(first.id(), second.id());
        assertTrue(first.id() < second.id());
    }


}
