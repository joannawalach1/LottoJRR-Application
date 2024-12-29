package com.javajuniorready.domain.numbergenerator;

import com.javajuniorready.infrastructure.numbergenerator.http.WinningNumbersFetcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class WinningNumberGenerator {
    private static final Logger logger = LoggerFactory.getLogger(WinningNumberGenerator.class);
    NumberGeneratorFacade numberGeneratorFacade;
    WinningNumbersFetcher winningNumbersFetcher;

    public String generateWinningNumbers(LocalDateTime lottoDrawDate) {
        String responseData = winningNumbersFetcher.fetchApiData("http://www.randomnumberapi.com:80/api/v1.0/random?min=1&max=99&count=6");
        logger.info(responseData);
        return responseData;
    }
}
