package com.javajuniorready.domain.resultannouncer;

import com.javajuniorready.domain.resultannouncer.dto.ResponseDto;

public class ResultResponseMapper {
    public static Object mapToDto;
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
    }
