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

import dev.kasse.engine.server.repository.query.MenuCategoryRepository;
import dev.kasse.engine.server.service.MenuCategoryService;
import dev.kasse.engine.server.state.TicketType;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;
import dev.kasse.engine.server.test.MockProvider.MenuCategoryMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@WebAppConfiguration
public class MenuCategoryControllerTest extends AbstractTest {

  public static final String PATH = "/menuCategory";

  public static final String ALL = PATH + "/all";

  public static final String ID = PATH + "/id";

  public static final String CATEGORY_ID = PATH + "/categoryId";

  public static final String TYPE = PATH + "/type";

  public static final String NAME = PATH + "/name";

  public static final String VISIBLE = PATH + "/visible";

  @Autowired
  private WebApplicationContext ctx;

  private MockMvc mockMvc;

  @Autowired
  @InjectMocks
  private MenuCategoryService menuCategoryService;

  @Mock
  private MenuCategoryRepository menuCategoryRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    menuCategoryService.setMenuCategoryRepository(menuCategoryRepository);
  }

  @Test
  public void getAllMenuCategories() throws Exception {

    Mockito.when(menuCategoryRepository.findAll()).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(ALL)).andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(2)))
        .andExpect(jsonPath("$[0].name").value("Pizza"))
        .andExpect(jsonPath("$[1].name").value("Pasta"));
  }


  @Test
  public void getById() throws Exception {

    Mockito.when(menuCategoryRepository.findById(Mockito.anyString())).thenReturn(
        MenuCategoryMockProvider.createMenuCategory("Pizza"));

    mockMvc.perform(get(ID).param("id", "categoryId"))
        .andExpect(status().isOk()).andExpect(jsonPath("name").value("Pizza"));

  }

  @Test
  public void getByMissingIdParameter() throws Exception {

    Mockito.when(menuCategoryRepository.findById(Mockito.anyString())).thenReturn(
        MenuCategoryMockProvider.createMenuCategory("Pizza"));

    mockMvc.perform(get(ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableId() throws Exception {

    Mockito.when(menuCategoryRepository.findById(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(ID).param("id", "categoryId"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByCategoryId() throws Exception {

    Mockito.when(menuCategoryRepository.findByCategoryId(Mockito.anyInt())).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(CATEGORY_ID)
        .param("categoryId", "101"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Pizza"));

  }

  @Test
  public void getByMissingCategoryIdParameter() throws Exception {

    Mockito.when(menuCategoryRepository.findByCategoryId(Mockito.anyInt())).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(CATEGORY_ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableCategoryId() throws Exception {

    Mockito.when(menuCategoryRepository.findByCategoryId(Mockito.anyInt())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(CATEGORY_ID)
      .param("categoryId", "101"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByCategoryName() throws Exception {

    Mockito.when(menuCategoryRepository.findByName(Mockito.anyString())).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(NAME)
        .param("name", "Pizza"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Pizza"));

  }

  @Test
  public void getByMissingNameParameter() throws Exception {

    Mockito.when(menuCategoryRepository.findByName(Mockito.anyString())).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(NAME))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableName() throws Exception {

    Mockito.when(menuCategoryRepository.findByName(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(NAME)
      .param("name", "Pizza"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByType() throws Exception {

    Mockito.when(menuCategoryRepository.findByType((TicketType) Mockito.anyObject())).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(TYPE)
        .param("type", TicketType.DINE_IN.name()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].type").value(TicketType.DINE_IN.name()));

  }

  @Test
  public void getByMissingTypeParameter() throws Exception {

    Mockito.when(menuCategoryRepository.findByType((TicketType) Mockito.anyObject())).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(TYPE))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableType() throws Exception {

    Mockito.when(menuCategoryRepository.findByType((TicketType) Mockito.anyObject())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(TYPE)
      .param("type", TicketType.DINE_IN.name()))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByVisible() throws Exception {

    Mockito.when(menuCategoryRepository.findByVisible(Mockito.anyBoolean())).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(VISIBLE)
        .param("visible", Boolean.TRUE.toString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].visible").value(Boolean.TRUE));

  }

  @Test
  public void getByMissingVisibleParameter() throws Exception {

    Mockito.when(menuCategoryRepository.findByVisible(Mockito.anyBoolean())).thenReturn(
        MenuCategoryMockProvider.createMenuCategories());

    mockMvc.perform(get(VISIBLE))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableVisible() throws Exception {

    Mockito.when(menuCategoryRepository.findByVisible(Mockito.anyBoolean())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(VISIBLE)
      .param("visible", Boolean.TRUE.toString()))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }
}
