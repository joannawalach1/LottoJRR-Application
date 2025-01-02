package com.javajuniorready.domain.resultannouncer;

import com.javajuniorready.domain.resultannouncer.dto.ResponseDto;
import com.javajuniorready.domain.resultchecker.dto.ResultDto;

public class ResultResponseMapper {

    static ResponseDto mapToDto(ResultResponse resultResponse) {
        return ResponseDto.builder()
                .drawDate(resultResponse.drawDate())
                .hash(resultResponse.hash())
                .hitNumbers(resultResponse.hitNumbers())
                .wonNumbers(resultResponse.wonNumbers())
                .wonNumbers(resultResponse.wonNumbers())
                .isWinner(resultResponse.isWinner())
                .build();
    }

    public static ResultResponse mapToEntity(ResultDto resultDto) {
        return ResultResponse.builder()
                .drawDate(resultDto.lottoDrawDate())
                .hash(resultDto.hash())
                .hitNumbers(resultDto.hitNumbers())
                .wonNumbers(resultDto.wonNumbers())
                .wonNumbers(resultDto.wonNumbers())
                .isWinner(resultDto.isWinner())
                .build();
    }
}

