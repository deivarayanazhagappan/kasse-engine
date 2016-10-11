package dev.kasse.engine.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kasse.engine.server.entities.Ticket;
import dev.kasse.engine.server.service.TicketService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RestController
@RequestMapping("/ticket")
public class TicketRestController {

  @Autowired
  public TicketService ticketService;

  @RequestMapping(method = { RequestMethod.POST })
  public Ticket saveTicket(@RequestBody Ticket ticket) {
    return ticketService.saveTicket(ticket);
  }

  @RequestMapping(path = "/id", method = { RequestMethod.DELETE })
  public void deleteTicketById(@RequestParam(required=true) String ticketId) {
    ticketService.deleteTicketById(ticketId);
  }

  @RequestMapping(path = "/all", method = { RequestMethod.DELETE })
  public void deleteAllTickets() {
    ticketService.deleteAllTickets();
  }

  @RequestMapping(path = "/all", method = { RequestMethod.GET })
  public List<Ticket> getAllTicket() {
    return ticketService.getAll();
  }

  @RequestMapping(path = "/id", method = { RequestMethod.GET })
  public Ticket getById(@RequestParam(required=true) String id) {
    return ticketService.getById(id);
  }

  @RequestMapping(path = "/state", method = { RequestMethod.GET })
  public List<Ticket> getByState(@RequestParam(required=true) String ticketState) {
    return ticketService.getByTicketState(ticketState);
  }

  @RequestMapping(path = "/paymentType", method = { RequestMethod.GET })
  public List<Ticket> getByPaymentType(@RequestParam(required=true) String paymentType) {
    return ticketService.getByPaymentType(paymentType);
  }

  @RequestMapping(path = "/tableNumber", method = { RequestMethod.GET })
  public List<Ticket> getByTableNumber(@RequestParam(required=true) int tableNumber) {
    return ticketService.getByTableNumber(tableNumber);
  }
}
