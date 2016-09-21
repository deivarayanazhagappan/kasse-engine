package dev.kasse.engine.server.test.query;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.Ticket;
import dev.kasse.engine.server.repository.query.ShopTableRepository;
import dev.kasse.engine.server.repository.query.TicketRepository;
import dev.kasse.engine.server.state.PaymentType;
import dev.kasse.engine.server.state.TicketState;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
public class TicketQueryTest extends AbstractTest {

  @Autowired
  protected TicketRepository ticketRepository;

  @Autowired
  protected ShopTableRepository shopTableRepository;

  @Before
  public void init() {
    ticketRepository.deleteAll();
    shopTableRepository.deleteAll();
  }

  @Test
  public void queryTicketState() {
    ticketRepository.save(createTicketsForStates());

    List<Ticket> allTickets = ticketRepository.findAll();
    assertEquals(5, allTickets.size());

    // open tickets
    List<Ticket> openTickets = ticketRepository
        .findByTicketState(TicketState.OPEN);
    assertEquals(1, openTickets.size());

    // closed tickets
    List<Ticket> closedTickets = ticketRepository
        .findByTicketState(TicketState.CLOSED);
    assertEquals(2, closedTickets.size());

    // paid tickets
    List<Ticket> paidTickets = ticketRepository
        .findByTicketState(TicketState.PAID);
    assertEquals(1, paidTickets.size());

    // reopened tickets
    List<Ticket> reopenedTickets = ticketRepository
        .findByTicketState(TicketState.REOPENED);
    assertEquals(1, reopenedTickets.size());

  }

  @Test
  public void queryTicketPaymentType() {
    ticketRepository.save(createTicketsForPaymentTypes());

    List<Ticket> allTickets = ticketRepository.findAll();
    assertEquals(4, allTickets.size());

    // card payment tickets
    List<Ticket> cardPaymentTickets = ticketRepository
        .findByPaymentType(PaymentType.CARD);
    assertEquals(1, cardPaymentTickets.size());

    // cash payment tickets
    List<Ticket> cashPaymentTickets = ticketRepository
        .findByPaymentType(PaymentType.CASH);
    assertEquals(2, cashPaymentTickets.size());

    // online payment tickets
    List<Ticket> onlinePaymentTickets = ticketRepository
        .findByPaymentType(PaymentType.ONLINE);
    assertEquals(1, onlinePaymentTickets.size());
  }

  @Test
  public void queryTicketByTableNumber() {
    ticketRepository.save(createTicketsForPaymentTypes());

    List<Ticket> allTickets = ticketRepository.findAll();
    assertEquals(4, allTickets.size());

    // card payment tickets
    List<Ticket> cardPaymentTickets = ticketRepository
        .findByPaymentType(PaymentType.CARD);
    assertEquals(1, cardPaymentTickets.size());

    // cash payment tickets
    List<Ticket> cashPaymentTickets = ticketRepository
        .findByPaymentType(PaymentType.CASH);
    assertEquals(2, cashPaymentTickets.size());

    // online payment tickets
    List<Ticket> onlinePaymentTickets = ticketRepository
        .findByPaymentType(PaymentType.ONLINE);
    assertEquals(1, onlinePaymentTickets.size());
  }

  @Test
  public void queryByTableNumber() {

    shopTableRepository.save(createShopTable(1,1));
    ticketRepository.save(createNewTicket(1));

    assertEquals(1, ticketRepository.findByTableNumber(1).size());
  }

  @Test
  public void queryByCustomerFirstName() {
    ticketRepository.save(createNewTicket(1));

    assertEquals(1, ticketRepository.findByCustomerFirstName("Kasse").size());
  }
}
