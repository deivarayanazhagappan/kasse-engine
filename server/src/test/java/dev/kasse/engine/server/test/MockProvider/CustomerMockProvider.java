package dev.kasse.engine.server.test.MockProvider;

import java.util.Arrays;
import java.util.List;

import dev.kasse.engine.server.entities.Customer;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public class CustomerMockProvider {

  public static List<Customer> createCustomers() {
    return (Arrays.asList(createCustomer("kasse")));
  }

  public static Customer createCustomer(String firstName) {
    Customer customer = new Customer();
    customer.setId("kasseId");
    customer.setFirstName(firstName);
    customer.setLastName(firstName+"LastName");
    customer.setBellName(firstName+"Bell");
    customer.setArea(firstName+"Area");
    customer.setCompanyName(firstName+"Company");
    customer.setEmail(firstName+"email");
    customer.setDoorNumber(firstName+"doorNr");
    customer.setLandmark(firstName+"Landmark");
    customer.setReferenceNumber(firstName+"ReferenceNr");
    customer.setStreet(firstName+"Street");
    customer.setTelephoneNumber(firstName+"12345");
    customer.setZipcode(firstName+"98568");
    return customer;  
  }
}
