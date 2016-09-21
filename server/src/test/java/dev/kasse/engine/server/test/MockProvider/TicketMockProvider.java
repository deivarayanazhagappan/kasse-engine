package dev.kasse.engine.server.test.MockProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import dev.kasse.engine.server.entities.ShopTable;
import dev.kasse.engine.server.entities.Ticket;
import dev.kasse.engine.server.entities.TicketItem;
import dev.kasse.engine.server.state.PaymentType;
import dev.kasse.engine.server.state.TicketState;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public class TicketMockProvider {

  public static List<Ticket> createTickets() {
    return (Arrays.asList(createNewTicket(1)));
  }

  public static Ticket createNewTicket(int tableNumber) {
    return createNewTicket(PaymentType.CASH, TicketState.OPEN, tableNumber,
        null, null);
  }

  public static Ticket createNewTicket(PaymentType paymentType,
      TicketState ticketState, int tableNumber, String userName,
      String customerName) {
    Ticket ticket = new Ticket();
    ticket.setId("kasseId");
    ticket.setCreateDate(new Date());
    ticket.setPaymentType(paymentType);
    ticket.setTicketState(ticketState);
    ticket.setSubTotal(15.25);
    ticket.setTotalTax(0.50);
    ticket.setTotal(15.50);

    // add 2 ticketItems
    List<TicketItem> ticketItems = createTicketItems();
    ticket.addTicketItems(ticketItems);
    ticket.setTable(createShopTable(tableNumber));
    return ticket;
  }

  public static List<TicketItem> createTicketItems() {
    List<TicketItem> ticketItems = new ArrayList<TicketItem>();

    ticketItems.add(createTicketItem("Cola"));
    ticketItems.add(createTicketItem("Fanta"));
    return ticketItems;
  }

  public static TicketItem createTicketItem(String itemName) {
    TicketItem ticketItem = new TicketItem();
    ticketItem.setBeverage(true);
    ticketItem.setCategoryName("CATEGORY");
    ticketItem.setGroupName("GROUP");
    ticketItem.setItemCount(2);
    ticketItem.setItemName(itemName);
    ticketItem.setSubTotal(6.00);
    ticketItem.setTotal(6.25);
    ticketItem.setTaxRate(0.25);
    return ticketItem;
  }

  public static ShopTable createShopTable(int tableNumber) {
    ShopTable table = new ShopTable();
    table.setId("kasseId");
    table.setFloor(1);
    table.setNumber(tableNumber);
    table.setOccupied(false);
    return table;
  }
}
