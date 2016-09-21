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

import dev.kasse.engine.server.repository.query.MenuItemRepository;
import dev.kasse.engine.server.service.MenuItemService;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;
import dev.kasse.engine.server.test.MockProvider.MenuItemMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@WebAppConfiguration
public class MenuItemControllerTest extends AbstractTest {

  public static final String PATH = "/menuItem";

  public static final String ALL = PATH + "/all";

  public static final String ID = PATH + "/id";

  public static final String GROUP_ID = PATH + "/groupId";

  public static final String ITEM_ID = PATH + "/itemId";
  
  public static final String NAME = PATH + "/name";

  @Autowired
  private WebApplicationContext ctx;

  private MockMvc mockMvc;

  @Autowired
  @InjectMocks
  private MenuItemService menuItemService;

  @Mock
  private MenuItemRepository menuItemRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    menuItemService.setMenuItemRepository(menuItemRepository);
  }

  @Test
  public void getAllMenuItems() throws Exception {

    Mockito.when(menuItemRepository.findAll()).thenReturn(
        MenuItemMockProvider.createMenuItems());

    mockMvc.perform(get(ALL)).andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(2)))
        .andExpect(jsonPath("$[0].name").value("Pizza"))
        .andExpect(jsonPath("$[1].name").value("Pasta"));
  }


  @Test
  public void getById() throws Exception {

    Mockito.when(menuItemRepository.findById(Mockito.anyString())).thenReturn(
        MenuItemMockProvider.createMenuItem("Pizza"));

    mockMvc.perform(get(ID).param("id", "itemId"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("name")
        .value("Pizza"));

  }

  @Test
  public void getByMissingIdParameter() throws Exception {

    Mockito.when(menuItemRepository.findById(Mockito.anyString())).thenReturn(
        MenuItemMockProvider.createMenuItem("Pizza"));

    mockMvc.perform(get(ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableId() throws Exception {

    Mockito.when(menuItemRepository.findById(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(ID).param("id", "itemId"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByGroupId() throws Exception {

    Mockito.when(menuItemRepository.findByGroupId(Mockito.anyString())).thenReturn(
        MenuItemMockProvider.createMenuItems());

    mockMvc.perform(get(GROUP_ID)
        .param("groupId", "groupId"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Pizza"));

  }

  @Test
  public void getByMissingGroupIdParameter() throws Exception {

    Mockito.when(menuItemRepository.findByGroupId(Mockito.anyString())).thenReturn(
        MenuItemMockProvider.createMenuItems());

    mockMvc.perform(get(GROUP_ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableGroupId() throws Exception {

    Mockito.when(menuItemRepository.findByGroupId(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(GROUP_ID)
      .param("groupId", "groupId"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByItemId() throws Exception {

    Mockito.when(menuItemRepository.findByItemId(Mockito.anyString())).thenReturn(
        MenuItemMockProvider.createMenuItems());

    mockMvc.perform(get(ITEM_ID)
        .param("itemId", "111"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Pizza"));

  }

  @Test
  public void getByMissingItemIdParameter() throws Exception {

    Mockito.when(menuItemRepository.findByItemId(Mockito.anyString())).thenReturn(
        MenuItemMockProvider.createMenuItems());

    mockMvc.perform(get(ITEM_ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableItemId() throws Exception {

    Mockito.when(menuItemRepository.findByItemId(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(ITEM_ID)
      .param("itemId", "111"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByName() throws Exception {

    Mockito.when(menuItemRepository.findByName(Mockito.anyString())).thenReturn(
        MenuItemMockProvider.createMenuItems());

    mockMvc.perform(get(NAME)
        .param("name", "Pizza"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Pizza"));

  }

  @Test
  public void getByMissingNameParameter() throws Exception {

    Mockito.when(menuItemRepository.findByName(Mockito.anyString())).thenReturn(
        MenuItemMockProvider.createMenuItems());

    mockMvc.perform(get(NAME))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableName() throws Exception {

    Mockito.when(menuItemRepository.findByName(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(NAME)
      .param("name", "Pizza"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }
}
