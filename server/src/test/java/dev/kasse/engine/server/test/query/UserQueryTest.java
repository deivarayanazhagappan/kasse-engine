package dev.kasse.engine.server.test.query;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.User;
import dev.kasse.engine.server.repository.query.UserRepository;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
public class UserQueryTest extends AbstractTest {

  public static final String FIRST_NAME_1 = "Kasse";
  public static final String FIRST_NAME_2 = "CashSystem";
 
  public User user;
 
  @Autowired
  protected UserRepository userRepository;

  @Before
  public void init() {
    userRepository.deleteAll();
    
    user = userRepository.save(createUser(FIRST_NAME_1));
    userRepository.save(createUser(FIRST_NAME_2));
  }

  @Test
  public void findAll() {
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    User user = users.get(0);
    assertEquals(FIRST_NAME_1,user.getFirstName());
 
    user = users.get(1);
    assertEquals(FIRST_NAME_2,user.getFirstName());
  }

  @Test
  public void findById() {
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertNotNull(userRepository.findById(user.getId()));
  }

  @Test
  public void findByInvalidId() {
    assertNull(userRepository.findById("123"));
    assertNull(userRepository.findById(null));
  }

  @Test
  public void containsFirstName() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(1, userRepository.containsFirstName("ka").size());
    assertEquals(1, userRepository.containsFirstName("KA").size());
    assertEquals(1, userRepository.containsFirstName("Cash").size());
    assertEquals(1, userRepository.containsFirstName("CASH").size());
    assertEquals(2, userRepository.containsFirstName("as").size());
  }

  @Test
  public void containsInvalidFirstName() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(0, userRepository.containsFirstName("aK").size());
    assertEquals(0, userRepository.containsFirstName("hsac").size());
  }

  @Test
  public void containsNullFirstName() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(0, userRepository.containsLastName(null).size());
  }

  @Test
  public void containsLastName() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(1, userRepository.containsLastName("kasSeLaSt").size());
    assertEquals(1, userRepository.containsLastName("kasselast").size());
    assertEquals(1, userRepository.containsLastName("CashSystemLast").size());
    assertEquals(1, userRepository.containsLastName("CASHSYSTEMLAST").size());
    assertEquals(2, userRepository.containsLastName("LAST").size());
  }

  @Test
  public void containsInvalidLastName() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(0, userRepository.containsLastName("OP").size());
  }

  @Test
  public void containsNullLastName() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(0, userRepository.containsLastName(null).size());
  }

  @Test
  public void containsTelephoneNumber() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(1, userRepository.containsTelephoneNumber(FIRST_NAME_1+"123").size());
    assertEquals(1, userRepository.containsTelephoneNumber(FIRST_NAME_2+"12").size());
    assertEquals(2, userRepository.containsTelephoneNumber("123").size());
  }

  @Test
  public void containsInvalidTelephoneNumber() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(0, userRepository.containsTelephoneNumber("989").size());
  }

  @Test
  public void containsNullTelephoneNumber() {
    
    List<User> users = userRepository.findAll();
    assertEquals(2, users.size());
 
    assertEquals(0, userRepository.containsTelephoneNumber(null).size());
  }

  @Test
  public void isAvailableForDelivery() {
    
    assertEquals(2, userRepository.findByAvailableForDelivery(true).size());
    assertEquals(0, userRepository.findByAvailableForDelivery(false).size());
  }

  @Test
  public void isDriver() {
    
    assertEquals(2, userRepository.findByIsDriver(true).size());
    assertEquals(0, userRepository.findByIsDriver(false).size());
  }
 }
