package com.javajuniorready.domain.numberreceiver;

import com.javajuniorready.domain.numberreceiver.dto.TicketDto;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
public class NumberReceiverFacadeTest {

    private final NumberTicketRepository numberTicketRepository = new InMemoryTicketRepositoryImpl();
    private final NumberReceiverFacadeConfigurationProperties properties = NumberReceiverFacadeConfigurationProperties.builder()
            .count(6)
            .highBound(99)
            .lowBound(1)
            .build();
    private final NumbersValidator numbersValidator = new NumbersValidator(properties);
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
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6)));
        Ticket ticket = numberReceiverFacade.createTicket(ticketDto);

        assertEquals(ticketDto.sixNumbers(), ticket.sixNumbers(), "The numbers in the ticket should match the input");
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionIfUserGaveLessThanSixNumbers() {
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5)));

        assertThrows(IllegalArgumentException.class,
                () -> numberReceiverFacade.createTicket(ticketDto),
                "Expected createTicket to throw IllegalArgumentException when less than 6 numbers are provided");
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionIfUserGaveMoreThanSixNumbers() {
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6, 7)));

        assertThrows(IllegalArgumentException.class,
                () -> numberReceiverFacade.createTicket(ticketDto),
                "Expected createTicket to throw IllegalArgumentException when less than 6 numbers are provided");
    }

    @Test
    public void shouldReturnIllegalArgumentExceptionIfUserGaveAtLeastOneNumberOutOfRange() {
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 120)));

        assertThrows(IllegalArgumentException.class,
                () -> numberReceiverFacade.createTicket(ticketDto),
                "Each number must be between 1 and 99");
    }

    @Test
    public void shouldSaveTicketsInRepositoryIfUserGaveCorrectData() {
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6)));
        Ticket savedTicket = new Ticket(ticketDto.id(), ticketDto.lottoDrawDate(), ticketDto.sixNumbers());
        InMemoryTicketRepositoryImpl ticketRepository = new InMemoryTicketRepositoryImpl();
        ticketRepository.save(savedTicket);
        Ticket ticketById = ticketRepository.findById(savedTicket.id())
                .orElseThrow(()-> new RuntimeException("Ticket not found"));

        assertNotNull(savedTicket);
        assertEquals(ticketById.sixNumbers(), savedTicket.sixNumbers());
    }

    @Test
    public void shouldGenerateValidLottoDrawDate() {
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.of(2024, 12, 29, 12, 0, 0), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6)));
        Ticket ticket = numberReceiverFacade.createTicket(ticketDto);

        assertNotNull(ticket.lottoDrawDate());
    }

    @Test
    public void shouldCorrectlyMapTicketDtoToTicketEntity() {
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6)));

        Ticket ticketEntity = NumberReceiverMapper.toTicketEntity(ticketDto);

        assertNotNull(ticketEntity);
        assertEquals(ticketDto.sixNumbers(), ticketEntity.sixNumbers());
        assertEquals(ticketDto.id(), ticketEntity.id());
    }

    @Test
    public void shouldFindTicketById() {
        NumbersValidator numbersValidator = new NumbersValidator(properties);
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6)));

        Ticket ticket = numberReceiverFacade.createTicket(ticketDto);

        Optional<Ticket> foundTicket = numberTicketRepository.findTicketById(ticket.id());

        assertNotNull(foundTicket);
        assertEquals(ticket.id(), foundTicket.get().id());
    }

    @Test
    public void shouldNotSaveTicketWithIncorrectData() {
        TicketDto ticketDto = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5)));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                numberReceiverFacade.createTicket(ticketDto));

        assertEquals("Format specifier '%d'", exception.getMessage());
    }

    @Test
    public void shouldThrowExceptionWhenTicketDtoIsNull() {
        TicketDto ticketDto = null;

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                numberReceiverFacade.createTicket(ticketDto));

        assertEquals("Ticket or sixNumbers cannot be null", exception.getMessage());
    }

    @Test
    public void shouldReturnAllTicketsWhenTheyExist() {
        NumberReceiverFacadeConfigurationProperties properties =
                NumberReceiverFacadeConfigurationProperties.builder()
                        .count(6)
                        .lowBound(1)
                        .highBound(99)
                        .build();

        NumbersValidator validator = new NumbersValidator(properties);
        TicketDto ticketDto1 = new TicketDto("1", LocalDateTime.now(), new SixNumbers(Set.of(1, 2, 3, 4, 5, 6)));
        TicketDto ticketDto2 = new TicketDto("2", LocalDateTime.now(), new SixNumbers(Set.of(10, 20, 30, 40, 50, 60)));

        numberReceiverFacade.createTicket(ticketDto1);
        numberReceiverFacade.createTicket(ticketDto2);

        List<Ticket> tickets = numberReceiverFacade.findAll();

        assertEquals(2, tickets.size(), "The ticket list should contain all created tickets");
    }

//    @Test
//    public void shouldThrowExceptionWhenTicketNotFoundById() {
//        int nonExistentId = 999;
//
//        TicketNotFoundException exception = assertThrows(TicketNotFoundException.class, () ->
//                numberReceiverFacade.findTicketById(nonExistentId));
//
//        assertEquals("Ticket with id " + nonExistentId + " not found", exception.getMessage());
//    }

    @Test
    public void shouldValidateCorrectNumbers() {
        Set<Integer> validNumbers = Set.of(1, 2, 3, 4, 5, 6);
        assertDoesNotThrow(() -> numbersValidator.validateUserNumbers(new SixNumbers(validNumbers)));
    }
}


