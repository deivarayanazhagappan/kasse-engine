package dev.kasse.engine.server.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.kasse.engine.server.entities.User;
import dev.kasse.engine.server.service.UserService;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RestController
@RequestMapping("/user")
public class UserRestController {

  @Autowired
  public UserService userService;

  @RequestMapping(method = { RequestMethod.POST })
  public void saveUser(@RequestParam(required=true) User user) {
    userService.saveUser(user);
  }

  @RequestMapping(method = { RequestMethod.DELETE })
  public void deleteCustomer(@RequestParam(required=true) User user) {
    userService.deleteUser(user);
  }

  @RequestMapping(path = "/all", method = { RequestMethod.GET })
  public List<User> getAllUsers() {
    return userService.getAll();
  }

  @RequestMapping(path = "/id", method = { RequestMethod.GET })
  public User getById(@RequestParam(required=true) String id) {
    return userService.getById(id);
  }

  @RequestMapping(path = "/firstName", method = { RequestMethod.GET })
  public List<User> getFirstName(@RequestParam(required=true) String firstName) {
    return userService.getByFirstName(firstName);
  }

  @RequestMapping(path = "/lastName", method = { RequestMethod.GET })
  public List<User> getByLastName(@RequestParam(required=true) String lastName) {
    return userService.getByLastName(lastName);
  }

  @RequestMapping(path = "/telephoneNumber", method = { RequestMethod.GET })
  public List<User> getByTelephoneNumber(@RequestParam(required=true) String telephoneNumber) {
    return userService.getByTelephoneNumber(telephoneNumber);
  }

  @RequestMapping(path = "/available", method = { RequestMethod.GET })
  public List<User> getByTelephoneNumber(@RequestParam(required=true) boolean availableForDelivery) {
    return userService.getByAvailability(availableForDelivery);
  }

  @RequestMapping(path = "/driver", method = { RequestMethod.GET })
  public List<User> getByIsDriver(@RequestParam(required=true) boolean isDriver) {
    return userService.getByDriver(isDriver);
  }
}
