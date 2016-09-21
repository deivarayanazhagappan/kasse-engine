package dev.kasse.engine.server.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kasse.engine.server.entities.Ticket;
import dev.kasse.engine.server.repository.query.TicketRepository;
import dev.kasse.engine.server.service.TicketService;
import dev.kasse.engine.server.state.PaymentType;
import dev.kasse.engine.server.state.TicketState;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@Service
public class TicketServiceImpl implements TicketService {

  @Autowired
  TicketRepository ticketRepository;

  public List<Ticket> getAll() {
    return ticketRepository.findAll();
  }

  public Ticket getById(String id) {
    return ticketRepository.findById(id);
  }

  public List<Ticket> getByTicketState(String ticketState) {

    TicketState state;
    if (ticketState.equals(TicketState.CLOSED.name())) {
      state = TicketState.CLOSED;
    } else if (ticketState.equals(TicketState.OPEN.name())) {
      state = TicketState.OPEN;
    } else if (ticketState.equals(TicketState.PAID.name())) {
      state = TicketState.PAID;
    } else if (ticketState.equals(TicketState.REOPENED.name())) {
      state = TicketState.REOPENED;
    } else {
      return new ArrayList<Ticket>();
    }

    return ticketRepository.findByTicketState(state);
  }

  public List<Ticket> getByPaymentType(String paymentType) {
    PaymentType type;
    if (paymentType.equals(PaymentType.CARD.name())) {
      type = PaymentType.CARD;
    } else if (paymentType.equals(PaymentType.CASH.name())) {
      type = PaymentType.CASH;
    } else if (paymentType.equals(PaymentType.ONLINE.name())) {
      type = PaymentType.ONLINE;
    } else {
      return new ArrayList<Ticket>();
    }

    return ticketRepository.findByPaymentType(type);
  }

  public List<Ticket> getByTableNumber(int tableNumber) {
    return ticketRepository.findByTableNumber(tableNumber);
  }

  public void setTicketRepository(TicketRepository ticketRepository) {
    this.ticketRepository = ticketRepository;
  }

  public Ticket saveTicket(Ticket ticket) {
    return ticketRepository.save(ticket);
  }

  public void deleteTicket(Ticket ticket) {
    ticketRepository.delete(ticket);  
  }

  public void deleteTicketById(String ticketId) {
   ticketRepository.delete(ticketId); 
  }

  public void deleteAllTickets() {
    ticketRepository.deleteAll();   
  }
}
