package com.javajuniorready.domain.numberreceiver;

import com.javajuniorready.domain.numberreceiver.dto.SixNumberDto;
import com.javajuniorready.domain.numberreceiver.dto.TicketDto;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
public class NumberReceiverFacade {
    private final NumberReceiverRepository numberReceiverRepository;
    private final NumbersValidator numbersValidator;
    private final NumberReceiverMapper numberReceiverMapper;
    private final NumberTicketRepository numberTicketRepository;
    private final LottoDrawDateGenerator lottoDrawDateGenerator;
    private int counter = 0;

    public SixNumbers takeUserNumbers(SixNumberDto sixNumberDto) {
        SixNumbers entity = NumberReceiverMapper.toEntity(sixNumberDto);
        SixNumbers validatedNumbers = numbersValidator.validateUserNumbers(entity);
        return numberReceiverRepository.saveUserNumbers(validatedNumbers);
    }

    public Ticket createTicket(SixNumbers sixNumbers) {
        LocalDateTime lottoDrawDate = lottoDrawDateGenerator.generateDrawDate();
        TicketDto ticketDto = TicketDto.builder()
                .id(++counter)
                .lottoDrawDate(lottoDrawDate)
                .sixNumbers(sixNumbers)
                .build();
        Ticket ticketEntity = NumberReceiverMapper.toTicketEntity(ticketDto);
        return numberTicketRepository.saveTickets(ticketEntity);
    }

    public Ticket findTicketById(Integer id) {
        return numberTicketRepository.findTicketById(id);
    }

    public List<Ticket> findAll() {
        return numberTicketRepository.findAll();
    }
}
