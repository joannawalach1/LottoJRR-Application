package com.javajuniorready.domain.numbergenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javajuniorready.domain.numbergenerator.dto.WinningNumbersDto;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
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
    private ObjectId testId;

    @BeforeEach
    void setUp() {
        testId = new ObjectId();
    }

    @Test
    public void shouldGenerateWinningNumbersBasedOnDrawDate() throws JsonProcessingException {
        WinningNumbers winningNumbers = new WinningNumbers(testId, LocalDateTime.of(2024,12,29, 0,0, 0), new WinningNumbersSet(Set.of(1, 2, 3, 4, 5, 6)));
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
}
