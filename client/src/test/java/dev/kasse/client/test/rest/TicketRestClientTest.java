package dev.kasse.client.test.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import dev.kasse.engine.client.entities.Ticket;
import dev.kasse.engine.client.rest.TicketRestClient;
import dev.kasse.engine.client.state.PaymentType;
import dev.kasse.engine.client.state.TicketState;
import dev.kasse.engine.server.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebIntegrationTest
public class TicketRestClientTest {

  @Before
  public void init() throws InterruptedException {
    Thread.sleep(10000);
  }

  @Test
  public void saveTicket() {
    TicketRestClient.deleteAllTickets();   
    TicketRestClient.saveTicket(createNewTicket());
    
    assertEquals(1, TicketRestClient.getAllTickets().size());
  }

  private Ticket createNewTicket() {
    Ticket ticket = new Ticket();
    ticket.setPaymentType(PaymentType.CARD);
    ticket.setTicketState(TicketState.CLOSED);
    ticket.setTotal(2.50);
    ticket.setTotalTax(0.50);
    ticket.setSubTotal(2.00);
    return ticket;
  }
}
