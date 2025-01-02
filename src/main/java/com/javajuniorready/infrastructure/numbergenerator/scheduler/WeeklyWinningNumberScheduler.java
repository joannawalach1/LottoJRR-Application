package com.javajuniorready.infrastructure.numbergenerator.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javajuniorready.domain.numbergenerator.NumberGeneratorFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WeeklyWinningNumberScheduler {

    private static final Logger logger = LoggerFactory.getLogger(WeeklyWinningNumberScheduler.class);
    private final NumberGeneratorFacade numberGeneratorFacade;

    public WeeklyWinningNumberScheduler(NumberGeneratorFacade numberGeneratorFacade) {
        this.numberGeneratorFacade = numberGeneratorFacade;
    }

    @Scheduled(cron = "0 55 15 * * SUN")
    public boolean generateWinningNumbers() throws JsonProcessingException {
        LocalDateTime drawDate = LocalDateTime.now();
        logger.info("Generating winning numbers started...");
        numberGeneratorFacade.generateLottoWinningNumbers(drawDate);
        logger.info("Generating winning numbers finished.");
        return false;
    }
}

