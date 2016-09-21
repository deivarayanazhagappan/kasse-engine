package dev.kasse.engine.server.test.entity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dev.kasse.engine.server.entities.User;
import dev.kasse.engine.server.repository.query.UserRepository;
import dev.kasse.engine.server.test.MockProvider.UserMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@EnableMongoRepositories("com.kasse.engine.Repository")
public class UserEntityTest {

  @Autowired
  private UserRepository userRepository;

  @Before
  public void init() {
    userRepository.deleteAll();
  }

  @Test
  public void insertUser() {
    userRepository.save(UserMockProvider.createUser("kasse"));
    assertEquals(1, userRepository.findAll().size());

    User user = userRepository.findAll().get(0);
    assertEquals("kasse", user.getFirstName());
    assertEquals("kasseLastName", user.getLastName());
    assertEquals("kassepassword", user.getPassword());
    assertEquals("kasse12345", user.getTelephoneNumber());
  }

  @Test
  public void updateUser() {
    userRepository.save(UserMockProvider.createUser("kasse"));
    assertEquals(1, userRepository.findAll().size());

    User user = userRepository.findAll().get(0);
    assertEquals("kasse", user.getFirstName());
    assertEquals("kasse12345", user.getTelephoneNumber());

    // update the user
    user.setFirstName("Dev");
    user.setTelephoneNumber("54868");

    userRepository.save(user);
    assertEquals(1, userRepository.findAll().size());

    user = userRepository.findAll().get(0);
    assertEquals("Dev", user.getFirstName());
    assertEquals("54868", user.getTelephoneNumber());
  }

  @Test
  public void deleteUser() {
    userRepository.save(UserMockProvider.createUser("kasse"));
    assertEquals(1, userRepository.findAll().size());

    User user = userRepository.findAll().get(0);
    assertNotNull(user);

    userRepository.delete(user);

    assertEquals(0, userRepository.findAll().size());
  }
}
