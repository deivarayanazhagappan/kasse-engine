package dev.kasse.engine.client.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import dev.kasse.engine.client.ApplicationHome;
import dev.kasse.engine.client.entities.Customer;

public class CustomerRestClient {

  public static final String PATH = ApplicationHome.SERVER_IP + "/customer";
  
  public static final String ALL_CUSTOMERS = PATH + "/all";

  public static final String ID = PATH + "/id?id=";

  public static final String DELETE_ID = PATH + "/id?customerId=";
  
  public static Customer saveCustomer(Customer customer) {
    RestTemplate restTemplate = new RestTemplate();
    HttpEntity<Customer> request = new HttpEntity<Customer>(customer);
    return restTemplate.postForObject(PATH, request, Customer.class);
  }

  public static void deleteAllCustomers() {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.delete(ALL_CUSTOMERS);
  }

  public static List<Customer> getAllCustomers() {
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<Customer[]> responseEntity  = restTemplate
        .getForEntity(ALL_CUSTOMERS, Customer[].class);

    Customer[] customerArray = responseEntity.getBody();
    if(customerArray == null) {
      return null;
    }

    List<Customer> customers = Arrays.asList(customerArray);
    return customers;
  }

  public static void deleteCustomerById(String id) {
    RestTemplate restTemplate = new RestTemplate();
    restTemplate.delete(DELETE_ID+id);
  }
  
  public static Customer getCustomerById(String id) {
    RestTemplate restTemplate = new RestTemplate();
    Customer customer  = restTemplate
        .getForObject(ID+id, Customer.class);

    return customer;
  }

}
