package dev.kasse.engine.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.kasse.engine.server.entities.User;
import dev.kasse.engine.server.repository.query.UserRepository;
import dev.kasse.engine.server.service.UserService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepository userRepository;

  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User getById(String id) {
    return userRepository.findById(id);
  }

  public List<User> getByFirstName(String firstName) {
    return userRepository.containsFirstName(firstName);
  }

  public List<User> getByLastName(String lastName) {
    return userRepository.containsLastName(lastName);
  }

  public void saveUser(User user) {
    userRepository.save(user);
  }

  public void deleteUser(User user) {
    userRepository.delete(user);
  }

  public void deleteUserById(String userId) {
    userRepository.delete(userId);
  }

  public void deleteAllUsers() {
    userRepository.deleteAll();
  }

  public List<User> getByTelephoneNumber(String telephoneNumber) {
    return userRepository.containsTelephoneNumber(telephoneNumber);
  }

  public List<User> getByAvailability(boolean isAvailable) {
    return userRepository.findByAvailableForDelivery(isAvailable);
  }

  public List<User> getByDriver(boolean isDriver) {
    return userRepository.findByIsDriver(isDriver);
  }

  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
}
