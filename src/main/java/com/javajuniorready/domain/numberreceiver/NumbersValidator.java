package com.javajuniorready.domain.numberreceiver;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
@RequiredArgsConstructor
public class NumbersValidator {
    private final NumberReceiverFacadeConfigurationProperties properties;
    SixNumbers validateUserNumbers(SixNumbers userNumber) {
         userNumber.userNumbers().stream()
                .limit(properties.count())
                .filter(number -> number <= properties.lowBound())
                .filter(number -> number >= properties.highBound())
                .collect(Collectors.toSet());
        return userNumber;
    }
}

