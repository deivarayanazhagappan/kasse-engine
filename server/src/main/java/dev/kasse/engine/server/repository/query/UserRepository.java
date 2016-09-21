package dev.kasse.engine.server.repository.query;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import dev.kasse.engine.server.entities.User;
import dev.kasse.engine.server.repository.query.custom.UserRepositoryCustom;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface UserRepository extends MongoRepository<User, String>,
    UserRepositoryCustom {

  public List<User> findAll();

  public User findById(String id);

  public List<User> findByIsDriver(boolean isDriver);

  public List<User> findByAvailableForDelivery(boolean availableForDelivery);

  
  
 }
