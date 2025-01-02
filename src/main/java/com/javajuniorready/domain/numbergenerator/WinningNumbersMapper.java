package com.javajuniorready.domain.numbergenerator;

import com.javajuniorready.domain.numbergenerator.dto.WinningNumbersDto;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbersMapper {
    private static ObjectId counter;

    public static WinningNumbers toWinningNumberEntity(WinningNumbersDto winningNumbersDto) {
        return WinningNumbers.builder()
                .WinningNumbersDrawDate(winningNumbersDto.WinningNumbersDrawDate())
                .winningNumbersSet(winningNumbersDto.winningNumbersSet())
                .build();
    }

    public static WinningNumbersDto toWinningNumberDto(WinningNumbers winningNumbers) {
        return WinningNumbersDto.builder()
                .WinningNumbersDrawDate(winningNumbers.WinningNumbersDrawDate())
                .winningNumbersSet(winningNumbers.winningNumbersSet())
                .build();
    }

    public static List<WinningNumbersDto> toWinningNumberDto(List<WinningNumbers> winningNumbers) {
        return winningNumbers.stream()
                .map(WinningNumbersMapper::toWinningNumberDto)
                .collect(Collectors.toList());
    }
}
