package dev.kasse.client.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.client.entities.ShopTable;
import dev.kasse.engine.client.entities.Ticket;
import dev.kasse.engine.client.rest.TicketRestClient;
import dev.kasse.engine.client.state.PaymentType;
import dev.kasse.engine.client.state.TicketState;
import dev.kasse.engine.server.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment=WebEnvironment.DEFINED_PORT)
public class TicketRestClientTest {

  @Before
  public void init() throws InterruptedException {
    TicketRestClient.deleteAllTickets();
    TicketRestClient.saveTicket(createNewTicket());
  }

  @Test
  public void saveTicket() {
    List<Ticket> allTickets = TicketRestClient.getAllTickets();
    assertEquals(1, allTickets.size());
    
    Ticket ticket = allTickets.get(0);
    assertNotNull(ticket);
    assertEquals(new Double(2.50), ticket.getTotal());
    assertEquals(new Double(0.50), ticket.getTotalTax());
    assertEquals(new Double(2.00), ticket.getSubTotal());
    assertEquals(PaymentType.CARD, ticket.getPaymentType());
    assertEquals(TicketState.CLOSED, ticket.getTicketState());
    assertEquals(new Double(2.00), ticket.getSubTotal());
  }

  @Test
  public void deleteByTicketId() {
    List<Ticket> allTickets = TicketRestClient.getAllTickets();
    assertEquals(1, allTickets.size());
    
    Ticket ticket = allTickets.get(0);
    assertNotNull(ticket);
    TicketRestClient.deleteTicketById(ticket.getId());
    
    allTickets = TicketRestClient.getAllTickets();
    assertEquals(0, allTickets.size());
  }

 
  @Test
  public void getByTicketId() {
    List<Ticket> allTickets = TicketRestClient.getAllTickets();
    assertEquals(1, allTickets.size());
    
    Ticket ticket = allTickets.get(0);
    assertNotNull(ticket);
    assertNotNull(TicketRestClient.getTicketById(ticket.getId()));
    
  }

  @Test
  public void getByTicketByPaymentType() {
    assertEquals(1, TicketRestClient
          .getTicketsByPaymentType(PaymentType.CARD).size());
    assertEquals(0, TicketRestClient
        .getTicketsByPaymentType(PaymentType.CASH).size());
  }

  @Test
  public void getByTicketByTicketState() {
    assertEquals(1, TicketRestClient
          .getTicketsByState(TicketState.CLOSED).size());
    assertEquals(0, TicketRestClient
        .getTicketsByState(TicketState.OPEN).size());
  }

  @Test
  public void getByTicketByTableNumber() {
    assertEquals(1, TicketRestClient
          .getTicketsByTableNumber(1).size());
    assertEquals(0, TicketRestClient
        .getTicketsByTableNumber(2).size());
  }

  private Ticket createNewTicket() {
    Ticket ticket = new Ticket();
    ticket.setPaymentType(PaymentType.CARD);
    ticket.setTicketState(TicketState.CLOSED);
    ticket.setTable(createNewTable());
    ticket.setTotal(2.50);
    ticket.setTotalTax(0.50);
    ticket.setSubTotal(2.00);
    return ticket;
  }

  private ShopTable createNewTable() {
    ShopTable table = new ShopTable();
    table.setFloor(1);
    table.setNumber(1);
    table.setOccupied(false);
    return table;
  }
}
