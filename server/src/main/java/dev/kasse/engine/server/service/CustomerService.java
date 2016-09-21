package dev.kasse.engine.server.service;

import java.util.List;

import dev.kasse.engine.server.entities.Customer;
import dev.kasse.engine.server.repository.query.CustomerRepository;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface CustomerService {

  // POST service
  public void saveCustomer(Customer customer);

  public void deleteCustomer(Customer customer);

  public void deleteCustomerById(String customerId);

  public void deleteAllCustomers();
  
  // GET service
  public List<Customer> getAll();

  public Customer getById(String id);

  public List<Customer> getByFirstName(String firstName);

  public List<Customer> getByLastName(String lastName);

  public List<Customer> getByStreet(String street);

  public List<Customer> getByZipcode(String zipcode);

  public List<Customer> getByTelephoneNumber(String telephoneNumber);

  public void setCustomerRepository(CustomerRepository customerRepository);

}
