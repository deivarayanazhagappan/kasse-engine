package dev.kasse.engine.server.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import dev.kasse.engine.server.entities.Customer;
import dev.kasse.engine.server.repository.query.custom.CustomerRepositoryCustom;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<Customer> containsFirstName(String firstName) {
    if(firstName== null) {
      return new ArrayList<Customer>();  
    }
    
    Query query = new Query();
    query.addCriteria(Criteria.where("firstName").regex(firstName, "i"));
    return mongoTemplate.find(query, Customer.class);
  }

  public List<Customer> containsLastName(String lastName) {
 
    if(lastName== null) {
      return new ArrayList<Customer>();  
    }
 
    Query query = new Query();
    query.addCriteria(Criteria.where("lastName").regex(lastName, "i"));
    return mongoTemplate.find(query, Customer.class);
  }

  public List<Customer> containsTelephoneNumber(String telephoneNumber) {

    if(telephoneNumber == null) {
      return new ArrayList<Customer>();  
    }
    
    Query query = new Query();
    query.addCriteria(Criteria.where("telephoneNumber").regex(telephoneNumber,
        "i"));
    return mongoTemplate.find(query, Customer.class);
  }

  public List<Customer> containsStreet(String street) {

    if(street == null) {
      return new ArrayList<Customer>();  
    }
    
    Query query = new Query();
    query.addCriteria(Criteria.where("street").regex(street, "i"));
    return mongoTemplate.find(query, Customer.class);
  }

  public List<Customer> containsZipcode(String zipcode) {

    if(zipcode == null) {
      return new ArrayList<Customer>();  
    }
    
    Query query = new Query();
    query.addCriteria(Criteria.where("zipcode").regex(zipcode, "i"));
    return mongoTemplate.find(query, Customer.class);
  }
}
