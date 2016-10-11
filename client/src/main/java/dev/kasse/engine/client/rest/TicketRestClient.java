package dev.kasse.engine.client.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import dev.kasse.engine.client.ApplicationHome;
import dev.kasse.engine.client.entities.Ticket;
import dev.kasse.engine.client.state.PaymentType;
import dev.kasse.engine.client.state.TicketState;

public class TicketRestClient {

  public static final String PATH = ApplicationHome.SERVER_IP + "/ticket";
  
  public static final String ALL_TICKETS = PATH + "/all";

  public static final String STATE = PATH + "/state?ticketState=";
  
  public static final String PAYMENT_TYPE = PATH + "/paymentType?paymentType=";
  
  public static final String TABLE_NUMBER = PATH + "/tableNumber?tableNumber=";
  
  public static final String ID = PATH + "/id?id=";

  public static final String DELETE_ID = PATH + "/id?ticketId=";
  
  public static final String DELETE_TICKET = PATH + "?ticket=";
  
  public static Ticket saveTicket(Ticket ticket) {
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Ticket> request = new HttpEntity<Ticket>(ticket);
    return restTemplate.postForObject(PATH, request, Ticket.class);
  }

  public static void deleteAllTickets() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.delete(ALL_TICKETS);
  }

  public static void deleteTicketById(String id) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.delete(DELETE_ID+id);
  }

  public static List<Ticket> getAllTickets() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Ticket[]> responseEntity  = restTemplate
        .getForEntity(ALL_TICKETS, Ticket[].class);

    Ticket[] ticketArray = responseEntity.getBody();
    if(ticketArray == null) {
      return null;
    }

    List<Ticket> tickets = Arrays.asList(ticketArray);
    return tickets;
  }

  public static Ticket getTicketById(String id) {
    RestTemplate restTemplate = new RestTemplate();
    Ticket ticket  = restTemplate
        .getForObject(ID+id, Ticket.class);

    return ticket;
  }

  public static List<Ticket> getTicketsByState(TicketState state) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Ticket[]> responseEntity  = restTemplate
        .getForEntity(STATE+state, Ticket[].class);

    Ticket[] ticketArray = responseEntity.getBody();
    if(ticketArray == null) {
      return null;
    }

    List<Ticket> tickets = Arrays.asList(ticketArray);
    return tickets;
  }

  public static List<Ticket> getTicketsByPaymentType(PaymentType paymentType) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Ticket[]> responseEntity  = restTemplate
        .getForEntity(PAYMENT_TYPE+paymentType, Ticket[].class);

    Ticket[] ticketArray = responseEntity.getBody();
    if(ticketArray == null) {
      return null;
    }

    List<Ticket> tickets = Arrays.asList(ticketArray);
    return tickets;
  }

  public static List<Ticket> getTicketsByTableNumber(int tableNumber) {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Ticket[]> responseEntity  = restTemplate
        .getForEntity(TABLE_NUMBER+tableNumber, Ticket[].class);

    Ticket[] ticketArray = responseEntity.getBody();
    if(ticketArray == null) {
      return null;
    }

    List<Ticket> tickets = Arrays.asList(ticketArray);
    return tickets;
  }
}
