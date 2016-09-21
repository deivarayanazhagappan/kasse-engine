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
import dev.kasse.engine.server.repository.query.ShopTableRepository;
import dev.kasse.engine.server.service.ShopTableService;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;
import dev.kasse.engine.server.test.MockProvider.ShopTableMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@WebAppConfiguration
public class ShopTableControllerTest extends AbstractTest {

  public static final String PATH = "/shopTable";

  public static final String ALL = PATH + "/all";

  public static final String ID = PATH + "/id";

  public static final String TABLE_NUMBER = PATH + "/tableNumber";

  public static final String FLOOR = PATH + "/floor";

  public static final String NUMBER_FLOOR = PATH + "/numberandfloor";

  @Autowired
  private WebApplicationContext ctx;

  private MockMvc mockMvc;

  @Autowired
  @InjectMocks
  private ShopTableService shopTableService;

  @Mock
  private ShopTableRepository shopTableRepository;

  @Mock
  Customer customer;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    shopTableService.setShopTableRepository(shopTableRepository);
  }

  // TODO: Post and delete test cases have to be added.

  // GET tests
  @Test
  public void getAllTables() throws Exception {

    Mockito.when(shopTableRepository.findAll()).thenReturn(
        ShopTableMockProvider.createShopTables());

    mockMvc.perform(get(ALL)).andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(1)));
  }

  @Test
  public void getById() throws Exception {

    Mockito.when(shopTableRepository.findOne(Mockito.anyString())).thenReturn(
        ShopTableMockProvider.createShopTable(1));

    mockMvc.perform(get(ID).param("id", "kasseId"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("id").value("kasseId"));
  }

  @Test
  public void getByUnavailableId() throws Exception {

    Mockito.when(shopTableRepository.findOne(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(ID).param("id", "kasseId"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingIdParameter() throws Exception {

    Mockito.when(shopTableRepository.findOne(Mockito.anyString())).thenReturn(
        ShopTableMockProvider.createShopTable(1));

    mockMvc.perform(get(ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByTableNumber() throws Exception {

    Mockito.when(shopTableRepository.findByNumber(Mockito.anyInt())).thenReturn(
        ShopTableMockProvider.createShopTables());

    mockMvc.perform(get(TABLE_NUMBER).param("number", "1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].number").value(1));
  }

  @Test
  public void getByUnavailableTableNumber() throws Exception {

    Mockito.when(shopTableRepository.findByNumber(Mockito.anyInt())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(TABLE_NUMBER).param("number", "1"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingNumberParameter() throws Exception {

    Mockito.when(shopTableRepository.findByNumber(Mockito.anyInt())).thenReturn(
        ShopTableMockProvider.createShopTables());

    mockMvc.perform(get(TABLE_NUMBER))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByFloor() throws Exception {

    Mockito.when(shopTableRepository.findByFloor(Mockito.anyInt())).thenReturn(
        ShopTableMockProvider.createShopTables());

    mockMvc.perform(get(FLOOR).param("floor", "1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].floor").value(1));
  }

  @Test
  public void getByUnavailableFloor() throws Exception {

    Mockito.when(shopTableRepository.findByFloor(Mockito.anyInt())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(FLOOR).param("floor", "1"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingFloorParameter() throws Exception {

    Mockito.when(shopTableRepository.findByFloor(Mockito.anyInt())).thenReturn(
        ShopTableMockProvider.createShopTables());

    mockMvc.perform(get(FLOOR))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByNumberAndFloor() throws Exception {

    Mockito.when(shopTableRepository.findByNumberAndFloor(Mockito.anyInt(),Mockito.anyInt())).thenReturn(
        ShopTableMockProvider.createShopTables());

    mockMvc.perform(get(NUMBER_FLOOR)
      .param("number", "1")
      .param("floor", "1"))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].floor").value(1))
      .andExpect(jsonPath("$[0].number").value(1));
  }

  @Test
  public void getByUnavailableNumberAndFloor() throws Exception {

    Mockito.when(shopTableRepository.findByNumberAndFloor(Mockito.anyInt(),Mockito.anyInt())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(NUMBER_FLOOR)
      .param("number", "1")
      .param("floor", "1"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByMissingNumberAndFloorParameter() throws Exception {

    Mockito.when(shopTableRepository.findByNumberAndFloor(Mockito.anyInt(),Mockito.anyInt())).thenReturn(
        ShopTableMockProvider.createShopTables());

    mockMvc.perform(get(NUMBER_FLOOR))
      .andExpect(status().is4xxClientError());

  }
}
