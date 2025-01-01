package com.javajuniorready.domain.resultchecker.dto;

import lombok.Builder;

import java.util.List;

@Builder
public record PlayerDto(String hash, List<ResultDto> results, String message) {
}
