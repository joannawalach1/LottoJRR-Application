package com.javajuniorready.domain.numberreceiver;

import com.javajuniorready.domain.numberreceiver.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RequiredArgsConstructor
public class NumberReceiverFacadeTest {

    private final NumberTicketRepository numberTicketRepository = new InMemoryTicketRepositoryImpl();
    private final NumbersValidator numbersValidator = new NumbersValidator();
    private final NumberReceiverMapper numberReceiverMapper = new NumberReceiverMapper();
    private final LottoDrawDateGenerator lottoDrawDateGenerator = new LottoDrawDateGenerator();
    private final NumberReceiverFacade numberReceiverFacade = new NumberReceiverFacade(
            numbersValidator,
            numberReceiverMapper,
            numberTicketRepository,
            lottoDrawDateGenerator
    );

    @Test
    public void shouldReturnCorrectTicketIfUserGaveSixNumbers() {
        TicketDto ticketDto = new TicketDto(1, LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6)));
        Ticket ticket = numberReceiverFacade.createTicket(ticketDto);

        assertEquals(ticketDto.sixNumbers(), ticket.sixNumbers(), "The numbers in the ticket should match the input");
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionIfUserGaveLessThanSixNumbers() {
        TicketDto ticketDto = new TicketDto(1, LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5)));

        assertThrows(IllegalArgumentException.class,
                () -> numberReceiverFacade.createTicket(ticketDto),
                "Expected createTicket to throw IllegalArgumentException when less than 6 numbers are provided");
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionIfUserGaveMoreThanSixNumbers() {
        TicketDto ticketDto = new TicketDto(1, LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6, 7)));

        assertThrows(IllegalArgumentException.class,
                () -> numberReceiverFacade.createTicket(ticketDto),
                "Expected createTicket to throw IllegalArgumentException when less than 6 numbers are provided");
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionIfUserGaveAtLeastOneNumberOutOfRange() {
        TicketDto ticketDto = new TicketDto(1, LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 120)));

        assertThrows(IllegalArgumentException.class,
                () -> numberReceiverFacade.createTicket(ticketDto),
                "Each number must be between 1 and 99");
    }



}