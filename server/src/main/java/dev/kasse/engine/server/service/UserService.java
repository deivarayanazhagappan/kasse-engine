package dev.kasse.engine.server.service;

import java.util.List;

import dev.kasse.engine.server.entities.User;
import dev.kasse.engine.server.repository.query.UserRepository;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public interface UserService {

  // POST service
  public void saveUser(User user);

  public void deleteUser(User user);

  public void deleteUserById(String userId);

  public void deleteAllUsers();
  
  // GET service
  public List<User> getAll();

  public User getById(String id);

  public List<User> getByFirstName(String firstName);

  public List<User> getByLastName(String lastName);

  public List<User> getByTelephoneNumber(String telephoneNumber);

  public List<User> getByAvailability(boolean isAvailable);
 
  public List<User> getByDriver(boolean isDriver);

  public void setUserRepository(UserRepository userRepository);

}
