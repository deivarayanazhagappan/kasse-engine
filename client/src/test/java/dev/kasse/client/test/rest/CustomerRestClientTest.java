package dev.kasse.client.test.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.client.entities.Customer;
import dev.kasse.engine.client.rest.CustomerRestClient;
import dev.kasse.engine.server.Application;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application.class}, webEnvironment=WebEnvironment.DEFINED_PORT)
public class CustomerRestClientTest {

  @Before
  public void init() throws InterruptedException {
    CustomerRestClient.deleteAllCustomers();
    CustomerRestClient.saveCustomer(createNewCustomer());
  }

  @Test
  public void saveCustomer() {
    List<Customer> allCustomers = CustomerRestClient.getAllCustomers();
    assertEquals(1, allCustomers.size());
    
    Customer customer = allCustomers.get(0);
    assertNotNull(customer);
    assertEquals("Mark", customer.getFirstName());
    assertEquals("Central Park", customer.getLandmark());
    assertEquals("Mark", customer.getBellName());
    assertEquals("Kasse corp", customer.getCompanyName());
    assertEquals("15", customer.getDoorNumber());
    assertEquals("mark@kasse.com", customer.getEmail());
    assertEquals("123", customer.getReferenceNumber());
    assertEquals("Berlinerstrasse", customer.getStreet());
    assertEquals("12345", customer.getTelephoneNumber());
    assertEquals("99999", customer.getZipcode());
  }

  @Test
  public void deleteByCustomerId() {
    List<Customer> allCustomers = CustomerRestClient.getAllCustomers();
    assertEquals(1, allCustomers.size());
    
    Customer customer = allCustomers.get(0);
    assertNotNull(customer);
    CustomerRestClient.deleteCustomerById(customer.getId());
    
    allCustomers = CustomerRestClient.getAllCustomers();
    assertEquals(0, allCustomers.size());
  }

 
  @Test
  public void getByCustomerId() {
    List<Customer> allCustomers = CustomerRestClient.getAllCustomers();
    assertEquals(1, allCustomers.size());
    
    Customer customer = allCustomers.get(0);
    assertNotNull(customer);
    assertNotNull(CustomerRestClient.getCustomerById(customer.getId()));
    
  }
 
  private Customer createNewCustomer() {
    Customer customer = new Customer();
    customer.setFirstName("Mark");
    customer.setLandmark("Central Park");
    customer.setBellName("Mark");
    customer.setCompanyName("Kasse corp");
    customer.setDoorNumber("15");
    customer.setEmail("mark@kasse.com");
    customer.setReferenceNumber("123");
    customer.setStreet("Berlinerstrasse");
    customer.setTelephoneNumber("12345");
    customer.setZipcode("99999");
    return customer;
  }
}
