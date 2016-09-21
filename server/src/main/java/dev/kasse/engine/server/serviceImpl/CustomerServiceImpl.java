package dev.kasse.engine.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kasse.engine.server.entities.Customer;
import dev.kasse.engine.server.repository.query.CustomerRepository;
import dev.kasse.engine.server.service.CustomerService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  public List<Customer> getAll() {
    return customerRepository.findAll();
  }

  public Customer getById(String id) {
    return customerRepository.findById(id);
  }

  public List<Customer> getByFirstName(String firstName) {
    return customerRepository.containsFirstName(firstName);
  }

  public List<Customer> getByLastName(String lastName) {
    return customerRepository.containsLastName(lastName);
  }

  public List<Customer> getByStreet(String street) {
    return customerRepository.containsStreet(street);
  }

  public List<Customer> getByZipcode(String zipcode) {
    return customerRepository.containsZipcode(zipcode);
  }

  public List<Customer> getByTelephoneNumber(String telephoneNumber) {
    return customerRepository.containsTelephoneNumber(telephoneNumber);
  }

  public void setCustomerRepository(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public void saveCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  public void deleteCustomer(Customer customer) {
    customerRepository.delete(customer);
  }

  public void deleteCustomerById(String customerId) {
    customerRepository.delete(customerId);
  }

  public void deleteAllCustomers() {
    customerRepository.deleteAll();
  }
}
