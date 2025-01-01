package com.javajuniorready.domain.resultchecker;

import com.javajuniorready.domain.numbergenerator.NumberGeneratorFacade;
import com.javajuniorready.domain.numberreceiver.NumberReceiverFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResultCheckerFacadeConfiguration {

    @Bean
    public ResultCheckerFacade resultCheckerFacade(
            NumberReceiverFacade numberReceiverFacade,
            NumberGeneratorFacade numberGeneratorFacade,
            PlayerRepository playerRepository) {
        WinnersRetriever winnersRetriever = new WinnersRetriever();
        return new ResultCheckerFacade(
                numberGeneratorFacade,
                numberReceiverFacade,
                playerRepository,
                winnersRetriever

        );
    }

}
