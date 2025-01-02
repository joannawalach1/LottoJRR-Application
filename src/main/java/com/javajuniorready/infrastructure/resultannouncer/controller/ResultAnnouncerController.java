package com.javajuniorready.infrastructure.resultannouncer.controller;

import com.javajuniorready.domain.resultannouncer.ResultAnnouncerFacade;
import com.javajuniorready.domain.resultannouncer.dto.ResultAnnouncerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ResultAnnouncerController {
    private final ResultAnnouncerFacade resultAnnouncerFacade;
    @PostMapping("/result")
    public ResponseEntity<ResultAnnouncerResponseDto> checkResult(String hash) {
        ResultAnnouncerResponseDto resultAnnouncerResponseDto = resultAnnouncerFacade.checkResult(hash);
        return ResponseEntity.status(HttpStatus.OK).body(resultAnnouncerResponseDto);
    }
}
