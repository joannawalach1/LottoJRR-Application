package com.javajuniorready.domain.numberreceiver;

import com.javajuniorready.domain.numberreceiver.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class NumberReceiverFacade {
    private final NumbersValidator numbersValidator;
    private final NumberReceiverMapper numberReceiverMapper;
    private final NumberTicketRepository numberTicketRepository;
    private final LottoDrawDateGenerator lottoDrawDateGenerator;

    private static final Logger logger = LoggerFactory.getLogger(NumberReceiverFacade.class);

    public Ticket createTicket(TicketDto ticketDto) {
        if (ticketDto == null || ticketDto.sixNumbers() == null) {
            throw new IllegalArgumentException("Ticket or sixNumbers cannot be null");
        }

        if (ticketDto.sixNumbers().userNumbers().size() != 6) {
            throw new IllegalArgumentException("SixNumbers must have exactly 6 numbers");
        }

        ticketDto.sixNumbers().userNumbers().forEach(number -> {
            if (number < 1 || number > 99) {
                throw new IllegalArgumentException("Each number must be between 1 and 99");
            }
        });

        LocalDateTime lottoDrawDate = lottoDrawDateGenerator.generateDrawDate();

        Ticket ticketEntity = Ticket.builder()
                .id(ticketDto.id())
                .sixNumbers(ticketDto.sixNumbers())
                .lottoDrawDate(lottoDrawDate)
                .build();
        numbersValidator.validateUserNumbers(ticketDto.sixNumbers());
        Ticket savedTicket = numberTicketRepository.saveTickets(ticketEntity);
        logger.info("Ticket with id:{} created: {}", ticketDto.id(), savedTicket);
        return savedTicket;
    }

    public Ticket findTicketById(int id) {
        Optional<Ticket> ticketById = numberTicketRepository.findTicketById(id);
        if (ticketById.isPresent()) {
            logger.info("Ticket with id:{} found: {}", id, ticketById.get());
            return ticketById.get();
        } else {
            logger.info("Ticket with id:{} not found", id);
            throw new TicketNotFoundException("Ticket with id " + id + " not found");
        }
    }

    public List<Ticket> findAll() {
        List<Ticket> allTickets = numberTicketRepository.findAll();
        if (allTickets.isEmpty()) {
            logger.info("No tickets found in the database.");
            throw new TicketNotFoundException("Tickets not found");
        } else {
            logger.info("All tickets in the database: {}", allTickets);
        }
        return allTickets;
    }

}
