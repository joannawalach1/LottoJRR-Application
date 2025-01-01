package com.javajuniorready.domain.resultannouncer;

import com.javajuniorready.domain.resultchecker.ResultCheckerFacade;
import org.springframework.context.annotation.Bean;

import java.time.Clock;

public class ResultAnnouncerConfiguration {
    @Bean
    public ResultAnnouncerFacade resultAnnouncerFacade(ResultCheckerFacade resultCheckerFacade, ResponseRepository responseRepository, Clock clock) {
       return new ResultAnnouncerFacade(
               resultCheckerFacade,
               responseRepository,
               clock
       );
    }
}
