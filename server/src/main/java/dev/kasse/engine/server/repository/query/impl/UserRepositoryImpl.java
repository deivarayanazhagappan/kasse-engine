package dev.kasse.engine.server.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import dev.kasse.engine.server.entities.User;
import dev.kasse.engine.server.repository.query.custom.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {

  @Autowired
  private MongoTemplate mongoTemplate;

  public List<User> containsFirstName(String firstName) {
    if(firstName== null) {
      return new ArrayList<User>();  
    }
    
    Query query = new Query();
    query.addCriteria(Criteria.where("firstName").regex(firstName, "i"));
    return mongoTemplate.find(query, User.class);
  }

  public List<User> containsLastName(String lastName) {
 
    if(lastName== null) {
      return new ArrayList<User>();  
    }
 
    Query query = new Query();
    query.addCriteria(Criteria.where("lastName").regex(lastName, "i"));
    return mongoTemplate.find(query, User.class);
  }

  public List<User> containsTelephoneNumber(String telephoneNumber) {

    if(telephoneNumber == null) {
      return new ArrayList<User>();  
    }
    
    Query query = new Query();
    query.addCriteria(Criteria.where("telephoneNumber").regex(telephoneNumber,
        "i"));
    return mongoTemplate.find(query, User.class);
  }
 }
