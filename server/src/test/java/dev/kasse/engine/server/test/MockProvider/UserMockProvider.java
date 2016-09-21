package dev.kasse.engine.server.test.MockProvider;

import java.util.Arrays;
import java.util.List;

import dev.kasse.engine.server.entities.User;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
public class UserMockProvider {

  public static List<User> createUsers() {
    return (Arrays.asList(createUser("kasse")));
  }

  public static User createUser(String firstName) {
    User user = new User();
    user.setFirstName(firstName);
    user.setId("KasseId");
    user.setLastName(firstName+"LastName");
    user.setTelephoneNumber(firstName+"12345");
    user.setPassword(firstName+"password");
    user.setDriver(true);
    user.setAvailableForDelivery(true);
    return user;
  }
}
