package dev.kasse.engine.server.test.query;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.Customer;
import dev.kasse.engine.server.repository.query.CustomerRepository;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
public class CustomerQueryTest extends AbstractTest {

  public static final String FIRST_NAME_1 = "Kasse";
  public static final String FIRST_NAME_2 = "CashSystem";
 
  public Customer customer;
 
  @Autowired
  protected CustomerRepository customerRepository;

  @Before
  public void init() {
    customerRepository.deleteAll();
    
    customer = customerRepository.save(createCustomer(FIRST_NAME_1));
    customerRepository.save(createCustomer(FIRST_NAME_2));
  }

  @Test
  public void findAll() {
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    Customer customer = customers.get(0);
    assertEquals(FIRST_NAME_1,customer.getFirstName());
 
    customer = customers.get(1);
    assertEquals(FIRST_NAME_2,customer.getFirstName());
  }

  @Test
  public void findById() {
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertNotNull(customerRepository.findById(customer.getId()));
  }

  @Test
  public void findByInvalidId() {
    assertNull(customerRepository.findById("123"));
    assertNull(customerRepository.findById(null));
  }

  @Test
  public void findByFirstName() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.findByFirstName(FIRST_NAME_1).size());
    assertEquals(1, customerRepository.findByFirstName(FIRST_NAME_2).size());
  }

  @Test
  public void containsFirstName() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.containsFirstName("ka").size());
    assertEquals(1, customerRepository.containsFirstName("KA").size());
    assertEquals(1, customerRepository.containsFirstName("Cash").size());
    assertEquals(1, customerRepository.containsFirstName("CASH").size());
    assertEquals(2, customerRepository.containsFirstName("as").size());
  }

  @Test
  public void containsInvalidFirstName() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsFirstName("aK").size());
    assertEquals(0, customerRepository.containsFirstName("hsac").size());
  }

  @Test
  public void containsNullFirstName() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsLastName(null).size());
  }

  @Test
  public void findByLastName() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.findByLastName(FIRST_NAME_1+"LastName").size());
    assertEquals(1, customerRepository.findByLastName(FIRST_NAME_2+"LastName").size());
  }

  @Test
  public void containsLastName() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.containsLastName("kasSeLaSt").size());
    assertEquals(1, customerRepository.containsLastName("kasselast").size());
    assertEquals(1, customerRepository.containsLastName("CashSystemLast").size());
    assertEquals(1, customerRepository.containsLastName("CASHSYSTEMLAST").size());
    assertEquals(2, customerRepository.containsLastName("LAST").size());
  }

  @Test
  public void containsInvalidLastName() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsLastName("OP").size());
  }

  @Test
  public void containsNullLastName() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsLastName(null).size());
  }

  @Test
  public void findByTelephoneNumber() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.findByTelephoneNumber(FIRST_NAME_1+"12345").size());
    assertEquals(1, customerRepository.findByTelephoneNumber(FIRST_NAME_2+"12345").size());
  }

  @Test
  public void containsTelephoneNumber() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.containsTelephoneNumber(FIRST_NAME_1+"123").size());
    assertEquals(1, customerRepository.containsTelephoneNumber(FIRST_NAME_2+"12").size());
    assertEquals(2, customerRepository.containsTelephoneNumber("123").size());
  }

  @Test
  public void containsInvalidTelephoneNumber() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsTelephoneNumber("989").size());
  }

  @Test
  public void containsNullTelephoneNumber() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsTelephoneNumber(null).size());
  }

  @Test
  public void findByStreet() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.findByStreet(FIRST_NAME_1+"Street").size());
    assertEquals(1, customerRepository.findByStreet(FIRST_NAME_2+"Street").size());
  }

  @Test
  public void containsStreet() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.containsStreet(FIRST_NAME_1+"St").size());
    assertEquals(1, customerRepository.containsStreet(FIRST_NAME_2+"Stre").size());
    assertEquals(2, customerRepository.containsStreet("Stre").size());
  }

  @Test
  public void containsInvalidStreet() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsStreet(FIRST_NAME_1+"OP").size());
  }

  @Test
  public void containsNullStreet() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsStreet(null).size());
  }

  @Test
  public void findByZipcode() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.findByZipcode(FIRST_NAME_1+"98568").size());
    assertEquals(1, customerRepository.findByZipcode(FIRST_NAME_2+"98568").size());
  }

  @Test
  public void containsZipcode() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(1, customerRepository.containsZipcode(FIRST_NAME_1+"98").size());
    assertEquals(1, customerRepository.containsZipcode(FIRST_NAME_2+"985").size());
    assertEquals(2, customerRepository.containsZipcode("98568").size());
  }

  @Test
  public void containsInvalidZipcode() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsZipcode("123").size());
  }

  @Test
  public void containsNullZipcode() {
    
    List<Customer> customers = customerRepository.findAll();
    assertEquals(2, customers.size());
 
    assertEquals(0, customerRepository.containsZipcode(null).size());
  }
}
