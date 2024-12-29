package com.javajuniorready.domain.numberreceiver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Configuration
public class NumberReceiverConfiguration {

    @Bean
    public NumberTicketRepository numberTicketRepository() {
        return new NumberTicketRepository() {

            private final List<Ticket> ticketsDatabase = new ArrayList<>();

            @Override
            public Ticket saveTickets(Ticket ticket) {
                ticketsDatabase.add(ticket);
                return ticket;
            }

            @Override
            public Optional<Ticket> findTicketById(Integer id) {
                return Optional.ofNullable(ticketsDatabase.stream()
                        .filter(ticket -> ticket.id() == (id))
                        .findFirst()
                        .orElse(null));
            }

            @Override
            public List<Ticket> findAll() {
                return ticketsDatabase;
            }
        };
    }

    @Bean
    public NumberReceiverFacade numberReceiverFacade(NumberTicketRepository numberTicketRepository) {
        NumbersValidator validator = new NumbersValidator();
        NumberReceiverMapper numberReceiverMapper = new NumberReceiverMapper();
        LottoDrawDateGenerator lottoDrawDateGenerator = new LottoDrawDateGenerator();
        return new NumberReceiverFacade(
                validator,
                numberReceiverMapper,
                numberTicketRepository,
                lottoDrawDateGenerator
        );
    }
}
