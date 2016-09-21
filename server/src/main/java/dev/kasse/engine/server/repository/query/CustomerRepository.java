package dev.kasse.engine.server.repository.query;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.kasse.engine.server.entities.Customer;
import dev.kasse.engine.server.repository.query.custom.CustomerRepositoryCustom;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface CustomerRepository extends MongoRepository<Customer, String>,
    CustomerRepositoryCustom {

  public List<Customer> findAll();

  public Customer findById(String id);

  public List<Customer> findByFirstName(String firstName);

  public List<Customer> findByLastName(String firstName);

  public List<Customer> findByTelephoneNumber(String telephoneNumber);

  public List<Customer> findByStreet(String street);

  public List<Customer> findByZipcode(String zipcode);
}
