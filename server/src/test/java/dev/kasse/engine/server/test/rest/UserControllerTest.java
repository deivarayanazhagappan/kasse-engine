package dev.kasse.engine.server.test.rest;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import dev.kasse.engine.server.entities.User;
import dev.kasse.engine.server.repository.query.UserRepository;
import dev.kasse.engine.server.service.UserService;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;
import dev.kasse.engine.server.test.MockProvider.UserMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@WebAppConfiguration
public class UserControllerTest extends AbstractTest {

  public static final String PATH = "/user";

  public static final String ALL = PATH + "/all";

  public static final String ID = PATH + "/id";

  public static final String FIRST_NAME = PATH + "/firstName";

  public static final String LAST_NAME = PATH + "/lastName";

  public static final String TELEPHONE_NUMBER = PATH + "/telephoneNumber";

  public static final String AVAILABLE = PATH + "/available";

  public static final String DRIVER = PATH + "/driver";

  @Autowired
  private WebApplicationContext ctx;

  private MockMvc mockMvc;

  @Autowired
  @InjectMocks
  private UserService userService;

  @Mock
  private UserRepository userRepository;

  @Mock
  User user;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    userService.setUserRepository(userRepository);
  }

  // TODO: Post and delete test cases have to be added.

  // GET tests
  @Test
  public void getAllUsers() throws Exception {

    Mockito.when(userRepository.findAll()).thenReturn(
        UserMockProvider.createUsers());

    mockMvc.perform(get(ALL)).andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(1)));
  }

  @Test
  public void getById() throws Exception {

    Mockito.when(userRepository.findById(Mockito.anyString())).thenReturn(
        UserMockProvider.createUser("CashSystem"));

    mockMvc.perform(get(ID).param("id", "KasseId"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").value("KasseId"));
  }

  @Test
  public void getByUnavailableId() throws Exception {

    Mockito.when(userRepository.findById("kasseId")).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(ID).param("id", "kasseId"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingIdParameter() throws Exception {

    Mockito.when(userRepository.findById("kasseId")).thenReturn(
        UserMockProvider.createUser("CashSystem"));

    mockMvc.perform(get(ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByFirstName() throws Exception {

    Mockito.when(userRepository.containsFirstName(Mockito.anyString())).thenReturn(
        UserMockProvider.createUsers());

    mockMvc.perform(get(FIRST_NAME).param("firstName", "kasse"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].firstName").value("kasse"));
  }

  @Test
  public void getByUnavailableFirstName() throws Exception {

    Mockito.when(userRepository.containsFirstName(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(FIRST_NAME).param("firstName", "kasse"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingFirstNameParameter() throws Exception {

    Mockito.when(userRepository.containsFirstName(Mockito.anyString())).thenReturn(
        null);

    mockMvc.perform(get(FIRST_NAME))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByLastName() throws Exception {

    Mockito.when(userRepository.containsLastName(Mockito.anyString())).thenReturn(
        UserMockProvider.createUsers());

    mockMvc.perform(get(LAST_NAME).param("lastName", "last"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].lastName").value("kasseLastName"));
  }

  @Test
  public void getByUnavailableLastName() throws Exception {

    Mockito.when(userRepository.containsLastName(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(LAST_NAME).param("lastName", "last"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingLastNameParameter() throws Exception {

    Mockito.when(userRepository.containsFirstName(Mockito.anyString())).thenReturn(
        null);

    mockMvc.perform(get(LAST_NAME))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByTelephoneNumber() throws Exception {

    Mockito.when(userRepository.containsTelephoneNumber(Mockito.anyString())).thenReturn(
        UserMockProvider.createUsers());

    mockMvc.perform(get(TELEPHONE_NUMBER).param("telephoneNumber", "12345"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].telephoneNumber").value("kasse12345"));
  }

  @Test
  public void getByUnavailableTelephoneNumber() throws Exception {

    Mockito.when(userRepository.containsTelephoneNumber(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(TELEPHONE_NUMBER).param("telephoneNumber", "12345"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingTelephoneNumberParameter() throws Exception {

    Mockito.when(userRepository.containsTelephoneNumber(Mockito.anyString())).thenReturn(
        null);

    mockMvc.perform(get(TELEPHONE_NUMBER))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByAvailability() throws Exception {

    Mockito.when(userRepository.findByAvailableForDelivery(Mockito.anyBoolean())).thenReturn(
        UserMockProvider.createUsers());

    mockMvc.perform(get(AVAILABLE).param("availableForDelivery", "true"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].availableForDelivery").value(true));
  }

  @Test
  public void getByNonExistingAvailability() throws Exception {

    Mockito.when(userRepository.findByAvailableForDelivery(Mockito.anyBoolean())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(AVAILABLE).param("availableForDelivery", "true"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingAvailabilityParameter() throws Exception {

    Mockito.when(userRepository.findByAvailableForDelivery(Mockito.anyBoolean())).thenReturn(
        null);

    mockMvc.perform(get(AVAILABLE))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByIsDriver() throws Exception {

    Mockito.when(userRepository.findByIsDriver(Mockito.anyBoolean())).thenReturn(
        UserMockProvider.createUsers());

    mockMvc.perform(get(DRIVER).param("isDriver", "true"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].driver").value(true));
  }

  @Test
  public void getByNonExistingIsDriver() throws Exception {

    Mockito.when(userRepository.findByIsDriver(Mockito.anyBoolean())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(DRIVER).param("isDriver", "true"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingIsDriverParameter() throws Exception {

    Mockito.when(userRepository.findByIsDriver(Mockito.anyBoolean())).thenReturn(
        null);

    mockMvc.perform(get(DRIVER))
      .andExpect(status().is4xxClientError());

  }

}
