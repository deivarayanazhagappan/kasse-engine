package dev.kasse.engine.server.repository.query;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.kasse.engine.server.entities.Ticket;
import dev.kasse.engine.server.repository.query.custom.TicketRepositoryCustom;
import dev.kasse.engine.server.state.PaymentType;
import dev.kasse.engine.server.state.TicketState;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface TicketRepository extends MongoRepository<Ticket, String>,
    TicketRepositoryCustom {

  public List<Ticket> findAll();

  public Ticket findById(String id);

  public List<Ticket> findByTicketState(TicketState state);

  public List<Ticket> findByPaymentType(PaymentType paymentType);

  public List<Ticket> findByCustomerFirstName(String firstName);

  public List<Ticket> findByTableNumber(int number);
}
