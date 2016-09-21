package dev.kasse.engine.server.test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.Ticket;
import dev.kasse.engine.server.entities.TicketItem;
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
@EnableMongoRepositories("com.kasse.engine.Repository")
public class TicketEntityTest extends AbstractTest {

  @Autowired
  private TicketRepository ticketRepository;

  @Before
  public void init() {
    ticketRepository.deleteAll();
  }

  @Test
  public void insertTicket() {
    ticketRepository.save(createNewTicket(1));
    assertEquals(1, ticketRepository.findAll().size());

    Ticket ticket = ticketRepository.findAll().get(0);
    assertEquals(PaymentType.CASH, ticket.getPaymentType());
    assertEquals(TicketState.OPEN, ticket.getTicketState());
    assertEquals(new Double(15.25), ticket.getSubTotal());
    assertEquals(new Double(0.50), ticket.getTotalTax());
    assertEquals(new Double(15.50), ticket.getTotal());

    assertNotNull(ticket.getCustomer());
    List<TicketItem> ticketItems = ticket.getTicketItems();
    assertEquals(2, ticketItems.size());

    TicketItem ticketItem = ticketItems.get(0);
    assertEquals("CATEGORY", ticketItem.getCategoryName());
    assertEquals("GROUP", ticketItem.getGroupName());
    assertEquals(2, ticketItem.getItemCount());
    assertEquals("101", ticketItem.getItemId());
    
    assertEquals(true, ticketItem.isBeverage());
    assertEquals("Cola", ticketItem.getItemName());
    assertEquals(new Double(6.00),ticketItem.getSubTotal());
    assertEquals(new Double(6.25), ticketItem.getTotal());
    assertEquals(new Double(0.25), ticketItem.getTaxRate());
    assertEquals(true, ticketItem.isShouldPrintToKitchen());
    assertEquals(false, ticketItem.isPrintedToKitchen());
    ticketItem.setPrintOrder(1);
  }

  @Test
  public void updateTicket() {
    ticketRepository.save(createNewTicket(1));
    assertEquals(1, ticketRepository.findAll().size());

    Ticket ticket = ticketRepository.findAll().get(0);
    assertEquals(PaymentType.CASH, ticket.getPaymentType());
    assertEquals(TicketState.OPEN, ticket.getTicketState());

    // update the ticket
    ticket.setPaymentType(PaymentType.CARD);
    ticket.setTicketState(TicketState.CLOSED);

    ticketRepository.save(ticket);
    assertEquals(1, ticketRepository.findAll().size());

    ticket = ticketRepository.findAll().get(0);
    assertEquals(PaymentType.CARD, ticket.getPaymentType());
    assertEquals(TicketState.CLOSED, ticket.getTicketState());
  }

  @Test
  public void deleteTicket() {
    ticketRepository.save(createNewTicket(1));
    assertEquals(1, ticketRepository.findAll().size());

    Ticket ticket = ticketRepository.findAll().get(0);
    assertEquals(PaymentType.CASH, ticket.getPaymentType());
    assertEquals(TicketState.OPEN, ticket.getTicketState());

    ticketRepository.delete(ticket);

    assertEquals(0, ticketRepository.findAll().size());
  }
}
