package dev.kasse.engine.client.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import dev.kasse.engine.client.state.PaymentType;
import dev.kasse.engine.client.state.TicketState;
import dev.kasse.engine.client.state.TicketType;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ticket {

  private String id;

  @DateTimeFormat(iso = ISO.DATE_TIME)
  private Date createDate;

  private Double subTotal;

  private Double total;

  private Double totalTax;

  private TicketState ticketState;

  private PaymentType paymentType;

  private List<TicketItem> ticketItems;

  private Customer customer;

  private ShopTable table;

  private User owner;

  private TicketType ticketType;

  public TicketType getTicketType() {
    return ticketType;
  }

  public void setTicketType(TicketType ticketType) {
    this.ticketType = ticketType;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Double getSubTotal() {
    return subTotal;
  }

  public void setSubTotal(Double subTotal) {
    this.subTotal = subTotal;
  }

  public Double getTotal() {
    return total;
  }

  public void setTotal(Double total) {
    this.total = total;
  }

  public Double getTotalTax() {
    return totalTax;
  }

  public void setTotalTax(Double totalTax) {
    this.totalTax = totalTax;
  }

  public TicketState getTicketState() {
    return ticketState;
  }

  public void setTicketState(TicketState ticketState) {
    this.ticketState = ticketState;
  }

  public PaymentType getPaymentType() {
    return paymentType;
  }

  public void setPaymentType(PaymentType paymentType) {
    this.paymentType = paymentType;
  }

  public List<TicketItem> getTicketItems() {
    if (ticketItems == null) {
      ticketItems = new ArrayList<TicketItem>();
    }
    return ticketItems;
  }

  public void addTicketItem(TicketItem item) {
    getTicketItems().add(item);
  }

  public void addTicketItems(List<TicketItem> ticketItems) {
    getTicketItems().addAll(ticketItems);
  }

  public void setTicketItems(List<TicketItem> ticketItems) {
    this.ticketItems = ticketItems;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public ShopTable getTable() {
    return table;
  }

  public void setTable(ShopTable table) {
    this.table = table;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  protected void calculateTotal() {
  }
}
