package com.javajuniorready.domain.numbergenerator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javajuniorready.infrastructure.numbergenerator.http.WinningNumbersFetcher;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Set;
@RequiredArgsConstructor
public class WinningNumberGenerator {
    private static final Logger logger = LoggerFactory.getLogger(WinningNumberGenerator.class);
    private final WinningNumbersFetcher winningNumbersFetcher = new WinningNumbersFetcher(new RestTemplate());
    private ObjectId counter;

    public WinningNumbers generateWinningNumbers(LocalDateTime lottoDrawDate) throws JsonProcessingException {
            String responseData = winningNumbersFetcher.fetchApiData("http://www.randomnumberapi.com:80/api/v1.0/random?min=1&max=99&count=6");

            logger.info("Api response: {}", responseData);

            ObjectMapper objectMapper = new ObjectMapper();
            Set<Integer> numbersList = objectMapper.readValue(responseData, new TypeReference<Set<Integer>>() {});

            logger.info("Generated numbers: {}", numbersList);

            WinningNumbersSet numbersSet = new WinningNumbersSet(numbersList);

            logger.info("Set of numbers: {}", numbersSet);
            WinningNumbers winningNumbers = WinningNumbers.builder()
                    .id(counter)
                    .WinningNumbersDrawDate(lottoDrawDate)
                    .winningNumbersSet(numbersSet)
                    .build();

            logger.info("WinningNumbers: " + lottoDrawDate + " created: {}", winningNumbers);

            return winningNumbers;
        }


    }
