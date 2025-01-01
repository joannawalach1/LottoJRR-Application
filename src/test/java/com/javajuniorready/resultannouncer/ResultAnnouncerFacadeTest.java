package com.javajuniorready.resultannouncer;

import com.javajuniorready.domain.resultannouncer.ResultAnnouncerConfiguration;
import com.javajuniorready.domain.resultannouncer.ResultAnnouncerFacade;
import com.javajuniorready.domain.resultannouncer.dto.ResultAnnouncerResponseDto;
import com.javajuniorready.domain.resultchecker.ResultCheckerFacade;
import com.javajuniorready.domain.resultchecker.dto.ResultDto;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Set;

import static com.javajuniorready.domain.resultannouncer.MessageResponse.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ResultAnnouncerFacadeTest {
    private final InMemoryResponseRepositoryTestImpl responseRepository = new InMemoryResponseRepositoryTestImpl();
    private final ResultCheckerFacade resultCheckerFacade = mock(ResultCheckerFacade.class);
    private final Clock clock = Clock.systemDefaultZone();

    @Test
    public void it_should_return_response_with_hash_does_not_exist_message_if_hash_does_not_exist() {
        //given
        String hash = "123";
        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration().resultAnnouncerFacade(resultCheckerFacade, responseRepository, Clock.systemUTC());

        when(resultCheckerFacade.findByTicketId(hash)).thenReturn(null);
        //when
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(hash);
        //then
        ResultAnnouncerResponseDto expectedResult = new ResultAnnouncerResponseDto(null, HASH_DOES_NOT_EXIST_MESSAGE.info);
        assertThat(resultAnnouncerResponseDto).isEqualTo(expectedResult);
    }

    @Test
    public void it_should_return_response_with_hash_does_not_exist_message_if_response_is_not_saved_to_db_yet() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        String hash = "123";
        ResultDto resultDto = ResultDto.builder()
                .hash("123")
                .result(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(1, 2, 3, 4, 9, 0))
                .lottoDrawDate(drawDate)
                .isWinner(true)
                .build();
        when(resultCheckerFacade.findByTicketId(hash)).thenReturn(resultDto);

        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration().resultAnnouncerFacade(resultCheckerFacade, responseRepository, Clock.systemUTC());
        ResultAnnouncerResponseDto resultAnnouncerResponseDto1 = resultAnnouncerFacade.checkResult(hash);
        String underTest = resultAnnouncerResponseDto1.responseDto().hash();
        //when
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(underTest);
        //then
        ResultAnnouncerResponseDto expectedResult = new ResultAnnouncerResponseDto(
                resultAnnouncerResponseDto.responseDto()
                , ALREADY_CHECKED.info);
        assertThat(resultAnnouncerResponseDto).isEqualTo(expectedResult);
    }

    @Test
    public void it_should_return_lose_message_if_winner_doesnt_exist() {
        //given
        LocalDateTime drawDate = LocalDateTime.of(2022, 12, 17, 12, 0, 0);
        String hash = "123";
        ResultDto resultDto = ResultDto.builder()
                .hash("123")
                .result(Set.of(1, 2, 3, 4, 5, 6))
                .hitNumbers(Set.of(7, 8, 9, 10, 11, 12))
                .lottoDrawDate(drawDate)
                .isWinner(false)
                .build();
        when(resultCheckerFacade.findByTicketId(hash)).thenReturn(resultDto);

        ResultAnnouncerFacade resultAnnouncerFacade = new ResultAnnouncerConfiguration().resultAnnouncerFacade(resultCheckerFacade, responseRepository, Clock.systemUTC());
        ResultAnnouncerResponseDto resultAnnouncerResponseDto1 = resultAnnouncerFacade.checkResult(hash);
        //then
        ResultAnnouncerResponseDto expectedResult = new ResultAnnouncerResponseDto(
                resultAnnouncerResponseDto1.responseDto()
                ,LOSE_MESSAGE.info);
        assertThat(resultAnnouncerResponseDto1).isEqualTo(expectedResult);
    }
}
