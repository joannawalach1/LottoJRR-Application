package com.javajuniorready.domain.resultannouncer;

import com.javajuniorready.domain.resultannouncer.dto.ResponseDto;
import com.javajuniorready.domain.resultannouncer.dto.ResultAnnouncerResponseDto;
import com.javajuniorready.domain.resultchecker.ResultCheckerFacade;
import com.javajuniorready.domain.resultchecker.dto.ResultDto;
import lombok.RequiredArgsConstructor;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.javajuniorready.domain.resultannouncer.MessageResponse.*;

@RequiredArgsConstructor
public class ResultAnnouncerFacade {
    private final ResultCheckerFacade resultCheckerFacade;
    private final ResponseRepository responseRepository;
    private final Clock clock;

    public ResultAnnouncerResponseDto checkResult(String hash) {
        if (responseRepository.existsById(hash)) {
            Optional<ResultResponse> resultResponseCached = responseRepository.findById(hash);
            if (resultResponseCached.isPresent()) {
                return new ResultAnnouncerResponseDto(ResultResponseMapper.mapToDto(resultResponseCached.get()), ALREADY_CHECKED.info);
            }
        }
        ResultDto resultDto = resultCheckerFacade.findWinnerByHash(hash);
        if (resultDto == null) {
            return new ResultAnnouncerResponseDto(null, HASH_DOES_NOT_EXIST_MESSAGE.info);
        }
        ResponseDto responseDto = buildResponseDto(resultDto);
        responseRepository.save(buildResponse(responseDto, LocalDateTime.now(clock)));
        if (responseRepository.existsById(hash) && !isAfterResultAnnouncementTime(resultDto)) {
            return new ResultAnnouncerResponseDto(responseDto, WAIT_MESSAGE.info);
        }
        if (resultCheckerFacade.findWinnerByHash(hash).isWinner()) {
            return new ResultAnnouncerResponseDto(responseDto, WIN_MESSAGE.info);
        }
        return new ResultAnnouncerResponseDto(responseDto, LOSE_MESSAGE.info);
    }

    private static ResultResponse buildResponse(ResponseDto responseDto, LocalDateTime now) {
        return ResultResponse.builder()
                .hash(responseDto.hash())
                .results(responseDto.results())
                .hitNumbers(responseDto.hitNumbers())
                .wonNumbers(responseDto.wonNumbers())
                .drawDate(responseDto.drawDate())
                .isWinner(responseDto.isWinner())
                .createdDate(now)
                .build();
    }

    private static ResponseDto buildResponseDto(ResultDto resultDto) {
        return ResponseDto.builder()
                .hash(resultDto.hash())
                .results(resultDto.result())
                .hitNumbers(resultDto.hitNumbers())
                .drawDate(resultDto.lottoDrawDate())
                .isWinner(resultDto.isWinner())
                .wonNumbers(resultDto.wonNumbers())
                .build();
    }

    private boolean isAfterResultAnnouncementTime(ResultDto resultDto) {
        LocalDateTime announcementDateTime = resultDto.lottoDrawDate();
        return LocalDateTime.now(clock).isAfter(announcementDateTime);
    }
}
