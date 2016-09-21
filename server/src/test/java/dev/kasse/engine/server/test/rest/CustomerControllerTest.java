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

import dev.kasse.engine.server.entities.Customer;
import dev.kasse.engine.server.repository.query.CustomerRepository;
import dev.kasse.engine.server.service.CustomerService;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;
import dev.kasse.engine.server.test.MockProvider.CustomerMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@WebAppConfiguration
public class CustomerControllerTest extends AbstractTest {

  public static final String PATH = "/customer";

  public static final String ALL = PATH + "/all";

  public static final String ID = PATH + "/id";

  public static final String FIRST_NAME = PATH + "/firstName";

  public static final String LAST_NAME = PATH + "/lastName";

  public static final String TELEPHONE_NUMBER = PATH + "/telephoneNumber";

  public static final String STREET = PATH + "/street";

  @Autowired
  private WebApplicationContext ctx;

  private MockMvc mockMvc;

  @Autowired
  @InjectMocks
  private CustomerService customerService;

  @Mock
  private CustomerRepository customerRepository;

  @Mock
  Customer customer;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    customerService.setCustomerRepository(customerRepository);
  }

  // TODO: Post and delete test cases have to be added.

  // GET tests
  @Test
  public void getAllCustomers() throws Exception {

    Mockito.when(customerRepository.findAll()).thenReturn(
        CustomerMockProvider.createCustomers());

    mockMvc.perform(get(ALL)).andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(1)));
  }

  @Test
  public void getById() throws Exception {

    Mockito.when(customerRepository.findById(Mockito.anyString())).thenReturn(
        CustomerMockProvider.createCustomer("CashSystem"));

    mockMvc.perform(get(ID).param("id", "kasseId"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").value("kasseId"));
  }

  @Test
  public void getByUnavailableId() throws Exception {

    Mockito.when(customerRepository.findById("kasseId")).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(ID).param("id", "kasseId"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingIdParameter() throws Exception {

    Mockito.when(customerRepository.findById("kasseId")).thenReturn(
        CustomerMockProvider.createCustomer("CashSystem"));

    mockMvc.perform(get(ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByFirstName() throws Exception {

    Mockito.when(customerRepository.containsFirstName(Mockito.anyString())).thenReturn(
        CustomerMockProvider.createCustomers());

    mockMvc.perform(get(FIRST_NAME).param("firstName", "kasse"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].firstName").value("kasse"));
  }

  @Test
  public void getByUnavailableFirstName() throws Exception {

    Mockito.when(customerRepository.containsFirstName(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(FIRST_NAME).param("firstName", "kasse"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingFirstNameParameter() throws Exception {

    Mockito.when(customerRepository.containsFirstName(Mockito.anyString())).thenReturn(
        null);

    mockMvc.perform(get(FIRST_NAME))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByLastName() throws Exception {

    Mockito.when(customerRepository.containsLastName(Mockito.anyString())).thenReturn(
        CustomerMockProvider.createCustomers());

    mockMvc.perform(get(LAST_NAME).param("lastName", "last"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].lastName").value("kasseLastName"));
  }

  @Test
  public void getByUnavailableLastName() throws Exception {

    Mockito.when(customerRepository.containsLastName(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(LAST_NAME).param("lastName", "last"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingLastNameParameter() throws Exception {

    Mockito.when(customerRepository.containsFirstName(Mockito.anyString())).thenReturn(
        null);

    mockMvc.perform(get(LAST_NAME))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByStreet() throws Exception {

    Mockito.when(customerRepository.containsStreet(Mockito.anyString())).thenReturn(
        CustomerMockProvider.createCustomers());

    mockMvc.perform(get(STREET).param("street", "street"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].street").value("kasseStreet"));
  }

  @Test
  public void getByUnavailableStreet() throws Exception {

    Mockito.when(customerRepository.containsStreet(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(STREET).param("street", "street"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingStreetParameter() throws Exception {

    Mockito.when(customerRepository.containsStreet(Mockito.anyString())).thenReturn(
        null);

    mockMvc.perform(get(STREET))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByTelephoneNumber() throws Exception {

    Mockito.when(customerRepository.containsTelephoneNumber(Mockito.anyString())).thenReturn(
        CustomerMockProvider.createCustomers());

    mockMvc.perform(get(TELEPHONE_NUMBER).param("telephoneNumber", "12345"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].telephoneNumber").value("kasse12345"));
  }

  @Test
  public void getByUnavailableTelephoneNumber() throws Exception {

    Mockito.when(customerRepository.containsTelephoneNumber(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(TELEPHONE_NUMBER).param("telephoneNumber", "12345"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingTelephoneNumberParameter() throws Exception {

    Mockito.when(customerRepository.containsTelephoneNumber(Mockito.anyString())).thenReturn(
        null);

    mockMvc.perform(get(TELEPHONE_NUMBER))
      .andExpect(status().is4xxClientError());

  }
}
