package com.javajuniorready.infrastructure.numbergenerator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javajuniorready.domain.numbergenerator.NumberGeneratorFacade;
import com.javajuniorready.domain.numbergenerator.WinningNumbers;
import com.javajuniorready.domain.numbergenerator.WinningNumbersMapper;
import com.javajuniorready.domain.numbergenerator.WinningNumbersNotFound;
import com.javajuniorready.domain.numbergenerator.dto.WinningNumbersDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WinningNumbersController {
    private final NumberGeneratorFacade numberGeneratorFacade;


    @GetMapping("/{date}")
    public ResponseEntity<WinningNumbersDto> findWinningNumberByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime date) {
        WinningNumbers winningNumbersByDate = numberGeneratorFacade.findWinningNumbersByDate(date)
                .orElseThrow(() -> new WinningNumbersNotFound("ggggggggggggg" + date));
        WinningNumbersDto winningNumberDto = WinningNumbersMapper.toWinningNumberDto(winningNumbersByDate);
        return ResponseEntity.status(HttpStatus.OK).body(winningNumberDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WinningNumbersDto>> findAllWinningNumbers() {
        List<WinningNumbers> winningNumbersByDate = numberGeneratorFacade.findAllWinningNumbers();
        List<WinningNumbersDto> winningNumberDto = WinningNumbersMapper.toWinningNumberDto(winningNumbersByDate);
        return ResponseEntity.status(HttpStatus.OK).body(winningNumberDto);
    }

    @PostMapping("/generateWinningNumbers")
    public WinningNumbersDto generateLottoWinningNumbers(@RequestBody LocalDateTime drawDate) throws JsonProcessingException {
        return numberGeneratorFacade.generateLottoWinningNumbers(drawDate);
    }
}
