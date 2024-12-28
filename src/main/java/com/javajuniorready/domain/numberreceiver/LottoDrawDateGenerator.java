package com.javajuniorready.domain.numberreceiver;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class LottoDrawDateGenerator {
    public LocalDateTime generateDrawDate() {
        return LocalDateTime.now();
    }

    LocalDateTime generateWinningNumbersDrawDate(LocalDateTime now) {
        LocalDateTime winningNumbersDrawDate = now.withHour(12).withMinute(0).withSecond(0).withNano(0);
        while (winningNumbersDrawDate.getDayOfWeek() != DayOfWeek.SATURDAY) {
            winningNumbersDrawDate = winningNumbersDrawDate.plusDays(1);
        }
        return winningNumbersDrawDate;

    }
}
