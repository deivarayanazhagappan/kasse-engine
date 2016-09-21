package dev.kasse.engine.server.repository.query;

import dev.kasse.engine.server.repository.query.custom.TicketRepositoryCustom;
import dev.kasse.engine.server.repository.query.impl.TicketRepositoryImpl;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 *         Handler for all repository query
 */
public class RepositoryQuery {

  public static TicketRepositoryCustom ticketRepositoryQuery;

  public static TicketRepositoryCustom getTicketRepositoryQuery() {
    if (ticketRepositoryQuery == null) {
      ticketRepositoryQuery = new TicketRepositoryImpl();
    }
    return ticketRepositoryQuery;
  }
}
