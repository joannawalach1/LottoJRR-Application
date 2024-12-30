package com.javajuniorready.infrastructure.numbergenerator.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javajuniorready.domain.numbergenerator.*;
import com.javajuniorready.domain.numbergenerator.dto.WinningNumbersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")

public class WinningNumbersController {
    @Autowired
    private NumberGeneratorFacade numberGeneratorFacade;
    @Autowired
    private WinningNumbersRepository repository;


    @GetMapping("/{date}")
    public ResponseEntity<WinningNumbersDto> findWinningNumberByDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime date) {
        WinningNumbers winningNumbersByDate = numberGeneratorFacade.findWinningNumbersByDate(date)
                .orElseThrow(() -> new WinningNumbersNotFound("ggggggggggggg" + date));
        WinningNumbersDto winningNumberDto = WinningNumbersMapper.toWinningNumberDto(winningNumbersByDate);
        return ResponseEntity.status(HttpStatus.OK).body(winningNumberDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<WinningNumbersDto>> findAllWinningNumbers() {
        List<WinningNumbers> winningNumbersByDate = repository.findAll();
        List<WinningNumbersDto> winningNumberDto = WinningNumbersMapper.toWinningNumberDto(winningNumbersByDate);
        return ResponseEntity.status(HttpStatus.OK).body(winningNumberDto);
    }

    @PostMapping("/generateWinningNumbers")
    public WinningNumbersDto generateLottoWinningNumbers(@RequestBody LocalDateTime drawDate) throws JsonProcessingException {
        return numberGeneratorFacade.generateLottoWinningNumbers(drawDate);
    }

    @PostMapping("/save")
    public String saveWinningNumbers(@RequestBody WinningNumbers winningNumbers) {
        repository.save(winningNumbers);
        return "Dane zapisane pomyślnie";
    }
}
