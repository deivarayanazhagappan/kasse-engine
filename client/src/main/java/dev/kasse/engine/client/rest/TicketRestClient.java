package dev.kasse.engine.client.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import dev.kasse.engine.client.ApplicationHome;
import dev.kasse.engine.client.entities.Ticket;

public class TicketRestClient {

  public static final String PATH = ApplicationHome.SERVER_IP + "/ticket";
  
  public static final String ALL_TICKETS = PATH + "/all";

  public static final String ID = PATH + "/id?id=";

  public static Ticket saveTicket(Ticket ticket) {
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Ticket> request = new HttpEntity<Ticket>(ticket);
    return restTemplate.postForObject(PATH, request, Ticket.class);
  }

  public static void deleteAllTickets() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.delete(ALL_TICKETS);
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
        .getForObject(ALL_TICKETS, Ticket.class);

    return ticket;
  }
}
