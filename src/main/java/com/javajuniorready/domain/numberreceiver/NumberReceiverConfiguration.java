package com.javajuniorready.domain.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class NumberReceiverConfiguration {

    @Bean
    public NumberReceiverRepository numberReceiverRepository() {
        return new NumberReceiverRepository() {
            @Override
            public SixNumbers saveUserNumbers(SixNumbers sixNumbers) {
                return null;
            }

            @Override
            public List<SixNumbers> findAll() {
                return List.of();
            }

            @Override
            public SixNumbers findById(int id) {
                return null;
            }
        };
    }
    
    @Bean
    public NumberTicketRepository numberTicketRepository() {
        return new NumberTicketRepository() {

            @Override
            public Ticket saveTickets(Ticket ticketEntity) {
                return null;
            }

            @Override
            public Ticket findTicketById(Integer id) {
                return null;
            }

            @Override
            public List<Ticket> findAll() {
                return List.of();
            }
        };
    }

    @Bean
    public NumberReceiverFacade numberReceiverFacade(
            NumberReceiverRepository numberReceiverRepository,
            NumberTicketRepository numberTicketRepository)
             {
        NumbersValidator validator = new NumbersValidator();
        NumberReceiverMapper numberReceiverMapper = new NumberReceiverMapper();
        LottoDrawDateGenerator lottoDrawDateGenerator = new LottoDrawDateGenerator();
                return new NumberReceiverFacade(
                numberReceiverRepository,
                validator,
                numberReceiverMapper,
                numberTicketRepository,
                lottoDrawDateGenerator


        );
    }
}
