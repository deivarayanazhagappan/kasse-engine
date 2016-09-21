package dev.kasse.engine.server.test.entity;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.Customer;
import dev.kasse.engine.server.entities.Ticket;
import dev.kasse.engine.server.repository.query.CustomerRepository;
import dev.kasse.engine.server.repository.query.TicketRepository;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@EnableMongoRepositories("com.kasse.engine.server.repository.query")
public class CustomerEntityTest extends AbstractTest {

  @Autowired
  private TicketRepository ticketRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Before
  public void init() {
    customerRepository.deleteAll();
    ticketRepository.deleteAll();
  }

  @Test
  public void insertCustomer() {
    customerRepository.save(createCustomer("Kasse"));

    List<Customer> customers = customerRepository.findAll();
    assertEquals(1, customers.size());

    Customer customer = customers.get(0);
    assertEquals("Kasse", customer.getFirstName());
    assertEquals("KasseLastName", customer.getLastName());
    assertEquals("KasseArea", customer.getArea());
    assertEquals("KasseBell", customer.getBellName());
    assertEquals("KasseCompany", customer.getCompanyName());
    assertEquals("KassedoorNr", customer.getDoorNumber());
    assertEquals("Kasseemail", customer.getEmail());
    assertEquals("KasseLandmark", customer.getLandmark());
    assertEquals("KasseReferenceNr", customer.getReferenceNumber());
    assertEquals("Kasse54321", customer.getSecondaryTelephone());
    assertEquals("KasseStreet", customer.getStreet());
    assertEquals("Kasse12345", customer.getTelephoneNumber());
    assertEquals("Kasse98568", customer.getZipcode());
  }

  @Test
  public void updateCustomer() {
    customerRepository.save(createCustomer("Kasse"));

    List<Customer> customers = customerRepository.findByFirstName("Kasse");
    Customer customer = customers.get(0);
    assertEquals("KasseLastName", customer.getLastName());
    
    customer.setLastName("KasseCashSystem");
    customerRepository.save(customer);

    customers = customerRepository.findByFirstName("Kasse");
    customer = customers.get(0);
    assertEquals("KasseCashSystem", customer.getLastName());
  }

  @Test
  public void deleteCustomer() {
    customerRepository.save(createCustomer("Kasse"));
    customerRepository.save(createCustomer("CashSystem"));
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
   
    Customer customer = customers.get(0);
    customerRepository.delete(customer);
 
    customers = customerRepository.findAll();
    assertEquals(1, customers.size());
  }

  @Test
  public void linkExistingCustomerToTicket() {
    Customer customer = customerRepository.save(createCustomer("Kasse"));

    assertEquals(1, customerRepository.findAll().size());

    ticketRepository.save(createNewTicket(customer));
    List<Ticket> tickets = ticketRepository.findAll();
    Ticket ticket = tickets.get(0);
    Customer ticketCustomer = ticket.getCustomer();
    assertNotNull(ticketCustomer);
    assertEquals("Kasse", ticketCustomer.getFirstName());
  }
}
