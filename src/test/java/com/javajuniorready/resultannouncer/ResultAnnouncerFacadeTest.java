package com.javajuniorready.resultannouncer;

import com.javajuniorready.domain.resultannouncer.ResultAnnouncerFacade;
import com.javajuniorready.domain.resultannouncer.ResultResponse;
import com.javajuniorready.domain.resultannouncer.dto.ResponseDto;
import com.javajuniorready.domain.resultannouncer.dto.ResultAnnouncerResponseDto;
import com.javajuniorready.domain.resultchecker.ResultCheckerFacade;
import com.javajuniorready.domain.resultchecker.dto.ResultDto;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;

import static com.javajuniorready.domain.resultannouncer.MessageResponse.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class ResultAnnouncerFacadeTest {
    private final InMemoryResponseRepositoryImpl responseRepository = new InMemoryResponseRepositoryImpl();
    private final ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);
    private final Clock clock = Clock.systemDefaultZone();
    private final ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerFacade(
            resultCheckerFacade,
            responseRepository,
            clock
    );

    @Test
    void should_return_already_checked_message_when_result_exists_in_repository() {
        String hash = "12345678";
        ResultResponse resultResponse = ResultResponse.builder()
                .hash(hash)
                .results(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .isWinner(true)
                .createdDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .build();

        responseRepository.save(resultResponse);

        ResultAnnouncerResponseDto response = resultAnnouncerFacade.checkResult(hash);

        assertEquals(HASH_DOES_NOT_EXIST_MESSAGE.info, response.message());
    }

    @Test
    void should_return_hash_does_not_exist_message_when_no_result_found() {
        String hash = "non_existing_hash";

        ResultResponse resultResponse = ResultResponse.builder()
                .hash(hash)
                .results(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .isWinner(true)
                .createdDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .build();

        responseRepository.save(resultResponse);

        ResultAnnouncerResponseDto response = resultAnnouncerFacade.checkResult(hash);

        assertEquals(HASH_DOES_NOT_EXIST_MESSAGE.info, response.message());
    }

    @Test
    void should_return_wait_message_when_result_is_not_after_announcement_time() {
        String hash = "some_hash";

        ResponseDto responseDto = ResponseDto.builder()
                .hash("12345678")
                .results(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .results(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .isWinner(true)
                .build();

        ResultResponse resultResponse = ResultResponse.builder()
                .hash(responseDto.hash())
                .results(responseDto.results())
                .hitNumbers(responseDto.hitNumbers())
                .wonNumbers(responseDto.wonNumbers())
                .createdDate(responseDto.drawDate())
                .drawDate(responseDto.drawDate())
                .isWinner(responseDto.isWinner())
                .build();

        ResultDto resultDto = ResultDto.builder()
                .hash("12345678")
                .result(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .userNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .lottoDrawDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .isWinner(true)
                .build();
        responseRepository.save(resultResponse);

        ResultAnnouncerResponseDto response = resultAnnouncerFacade.checkResult(hash);

        assertEquals(HASH_DOES_NOT_EXIST_MESSAGE.info, response.message());
    }

    @Test
    void should_return_win_message_when_result_is_winner() {
        String hash = "some_hash";
        ResponseDto responseDto = ResponseDto.builder()
                .hash("12345678")
                .results(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .results(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .isWinner(true)
                .build();

        ResultResponse resultResponse = ResultResponse.builder()
                .hash(responseDto.hash())
                .results(responseDto.results())
                .hitNumbers(responseDto.hitNumbers())
                .wonNumbers(responseDto.wonNumbers())
                .createdDate(responseDto.drawDate())
                .drawDate(responseDto.drawDate())
                .isWinner(responseDto.isWinner())
                .build();

        ResultDto resultDto = ResultDto.builder()
                .hash("12345678")
                .result(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .userNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .lottoDrawDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .isWinner(true)
                .build();
        responseRepository.save(resultResponse);

        ResultAnnouncerResponseDto response = resultAnnouncerFacade.checkResult(hash);

        assertEquals(HASH_DOES_NOT_EXIST_MESSAGE.info, response.message());
    }

    @Test
    void should_return_lose_message_when_result_is_not_winner() {
        String hash = "some_hash";
        ResultResponse resultResponse = ResultResponse.builder()
                .hash(hash)
                .results(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .drawDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .isWinner(true)
                .createdDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .build();

        responseRepository.save(resultResponse);

        ResultAnnouncerResponseDto response = resultAnnouncerFacade.checkResult(hash);

        assertEquals(HASH_DOES_NOT_EXIST_MESSAGE.info, response.message());
    }

    @Test
    void should_save_response_to_repository_when_result_is_checked() {
        String hash = "some_hash";
        ResultDto resultDto = ResultDto.builder()
                .hash("12345678")
                .result(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .userNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .wonNumbers(Set.of(1, 2, 3, 4, 5, 6))
                .lottoDrawDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .isWinner(true)
                .build();

        ResultResponse resultResponse = ResultResponse.builder()
                .hash(resultDto.hash())
                .results(resultDto.result())
                .hitNumbers(resultDto.hitNumbers())
                .wonNumbers(resultDto.wonNumbers())
                .createdDate(LocalDateTime.of(2025, 1, 1, 12, 0, 0))
                .drawDate(resultDto.lottoDrawDate())
                .isWinner(resultDto.isWinner())
                .build();

        responseRepository.save(resultResponse);

        ResultAnnouncerResponseDto response = resultAnnouncerFacade.checkResult(hash);

        assertEquals(HASH_DOES_NOT_EXIST_MESSAGE.info, response.message());
    }
}
