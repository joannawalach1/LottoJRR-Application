package com.javajuniorready.domain.numbergenerator;

import com.javajuniorready.domain.numbergenerator.dto.WinningNumbersDto;
import com.javajuniorready.domain.numberreceiver.NumberReceiverMapper;

import java.util.List;
import java.util.stream.Collectors;

public class WinningNumbersMapper {
    public static WinningNumbers toWinningNumberEntity(WinningNumbersDto winningNumbersDto) {
        return WinningNumbers.builder()
                .id(winningNumbersDto.id())
                .WinningNumbersDrawDate(winningNumbersDto.WinningNumbersDrawDate())
                .winningNumbersSet(winningNumbersDto.winningNumbersSet())
                .build();
    }

    public static WinningNumbersDto toWinningNumberDto(WinningNumbers winningNumbers) {
        return WinningNumbersDto.builder()
                .id(winningNumbers.id())
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
