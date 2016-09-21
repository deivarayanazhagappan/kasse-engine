package dev.kasse.engine.server.repository.query.custom;

import java.util.List;

import dev.kasse.engine.server.entities.Customer;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 * Custom interface for customer repository
 */
public interface CustomerRepositoryCustom {

  public List<Customer> containsFirstName(String firstName);

  public List<Customer> containsLastName(String lastName);

  public List<Customer> containsTelephoneNumber(String telephoneNumber);

  public List<Customer> containsStreet(String street);

  public List<Customer> containsZipcode(String zipcode);
}
