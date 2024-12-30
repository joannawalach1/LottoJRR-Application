package com.javajuniorready.domain.numbergenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javajuniorready.domain.numbergenerator.dto.WinningNumbersDto;
import com.javajuniorready.domain.numberreceiver.NumberReceiverFacade;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class NumberGeneratorFacade {
    private static final Logger logger = LoggerFactory.getLogger(NumberGeneratorFacade.class);
    private final WinningNumberGenerator winningNumberGenerator;
    private final WinningNumbersRepository winningNumberRepository;

    public WinningNumbersDto generateLottoWinningNumbers(LocalDateTime lottoDrawDate) throws JsonProcessingException {
        logger.info("Generating winning numbers for draw date: {}", lottoDrawDate);
        WinningNumbers winningNumbers = winningNumberGenerator.generateWinningNumbers(lottoDrawDate);

        if (winningNumbers == null) {
            logger.error("Failed to generate winning numbers for date: {}", lottoDrawDate);
            throw new IllegalStateException("Unable to generate winning numbers.");
        }

        WinningNumbers winningNumbersSaved = winningNumberRepository.save(winningNumbers);
        logger.info("Winning numbers saved: {}", winningNumbersSaved);

        if (winningNumbersSaved == null) {
            logger.error("Failed to save winning numbers.");
            throw new IllegalStateException("Unable to save winning numbers.");
        }

        WinningNumbersDto winningNumberDto = WinningNumbersMapper.toWinningNumberDto(winningNumbersSaved);
        logger.info("WinningNumbers with id:{} created: {}", winningNumbersSaved.id(), winningNumbersSaved);

        return winningNumberDto;
    }

    public Optional<WinningNumbers> findWinningNumbersByDate(LocalDateTime lottoDrawDate) {
        return winningNumberRepository.findWinningNumbersByWinningNumbersDrawDate(lottoDrawDate);
    }

    public List<WinningNumbers> findAllWinningNumbers() {
        return winningNumberRepository.findAll();
    }
}
