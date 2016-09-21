package dev.kasse.engine.server.service;

import java.util.List;

import dev.kasse.engine.server.entities.Ticket;
import dev.kasse.engine.server.repository.query.TicketRepository;

public interface TicketService {

  //POST service
  public Ticket saveTicket(Ticket ticket);

  public void deleteTicket(Ticket ticket);

  public void deleteTicketById(String ticketId);

  public void deleteAllTickets();
 
  public List<Ticket> getAll();

  public Ticket getById(String id);

  public List<Ticket> getByTicketState(String ticketState);

  public List<Ticket> getByPaymentType(String paymentType);

  public List<Ticket> getByTableNumber(int tableNumber);

  public void setTicketRepository(TicketRepository ticketRepository);

}
