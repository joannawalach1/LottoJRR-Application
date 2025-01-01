package com.javajuniorready.domain.numberreceiver;

import com.javajuniorready.domain.numberreceiver.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
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
                .id(new ObjectId())
                .sixNumbers(ticketDto.sixNumbers())
                .lottoDrawDate(lottoDrawDate)
                .build();
        numbersValidator.validateUserNumbers(ticketDto.sixNumbers());
        Ticket savedTicket = numberTicketRepository.save(ticketEntity);
        logger.info("Ticket with id:{} created: {}", ticketDto.id(), savedTicket);
        return savedTicket;
    }

    public Ticket findTicketById(int id) {
        return numberTicketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + id + " not found"));
    }

    public List<Ticket> findAll() {
        List<Ticket> allTickets = numberTicketRepository.findAll();

        return Optional.of(allTickets)
                .filter(tickets -> !tickets.isEmpty())
                .orElseThrow(() -> {
                    logger.info("No tickets found in the database.");
                    return new TicketNotFoundException("Tickets not found");
                });
    }
}