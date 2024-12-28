package com.javajuniorready.domain.numberreceiver;

import java.util.stream.Collectors;

public class NumbersValidator {
    SixNumbers validateUserNumbers(SixNumbers userNumber) {
         userNumber.userNumbers().stream()
                .limit(6)
                .filter(number -> number <= 1)
                .filter(number -> number >= 99)
                .collect(Collectors.toSet());
        return userNumber;
    }
}

