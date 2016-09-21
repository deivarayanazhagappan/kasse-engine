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

import dev.kasse.engine.server.repository.query.MenuGroupRepository;
import dev.kasse.engine.server.service.MenuGroupService;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;
import dev.kasse.engine.server.test.MockProvider.MenuGroupMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@WebAppConfiguration
public class MenuGroupControllerTest extends AbstractTest {

  public static final String PATH = "/menuGroup";

  public static final String ALL = PATH + "/all";

  public static final String ID = PATH + "/id";

  public static final String CATEGORY_ID = PATH + "/categoryId";

  public static final String GROUP_ID = PATH + "/groupId";

  public static final String NAME = PATH + "/name";

  public static final String GAENGE = PATH + "/gaenge";

  @Autowired
  private WebApplicationContext ctx;

  private MockMvc mockMvc;

  @Autowired
  @InjectMocks
  private MenuGroupService menuGroupService;

  @Mock
  private MenuGroupRepository menuGroupRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    menuGroupService.setMenuGroupRepository(menuGroupRepository);
  }

  @Test
  public void getAllMenuGroups() throws Exception {

    Mockito.when(menuGroupRepository.findAll()).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(ALL)).andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(2)))
        .andExpect(jsonPath("$[0].name").value("Margherita"))
        .andExpect(jsonPath("$[1].name").value("Pepporoni"));
  }


  @Test
  public void getById() throws Exception {

    Mockito.when(menuGroupRepository.findById(Mockito.anyString())).thenReturn(
        MenuGroupMockProvider.createMenuGroup("Margherita"));

    mockMvc.perform(get(ID).param("id", "groupId"))
        .andExpect(status().isOk()).andExpect(jsonPath("name").value("Margherita"));

  }

  @Test
  public void getByMissingIdParameter() throws Exception {

    Mockito.when(menuGroupRepository.findById(Mockito.anyString())).thenReturn(
        MenuGroupMockProvider.createMenuGroup("Margherita"));

    mockMvc.perform(get(ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableId() throws Exception {

    Mockito.when(menuGroupRepository.findById(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(ID).param("id", "groupId"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByCategoryId() throws Exception {

    Mockito.when(menuGroupRepository.findByCategoryId(Mockito.anyString())).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(CATEGORY_ID)
        .param("categoryId", "999"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Margherita"));

  }

  @Test
  public void getByMissingCategoryIdParameter() throws Exception {

    Mockito.when(menuGroupRepository.findByCategoryId(Mockito.anyString())).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(CATEGORY_ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableCategoryId() throws Exception {

    Mockito.when(menuGroupRepository.findByCategoryId(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(CATEGORY_ID)
      .param("categoryId", "999"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByGroupId() throws Exception {

    Mockito.when(menuGroupRepository.findByGroupId(Mockito.anyInt())).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(GROUP_ID)
        .param("groupId", "111"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Margherita"));

  }

  @Test
  public void getByMissingGroupIdParameter() throws Exception {

    Mockito.when(menuGroupRepository.findByGroupId(Mockito.anyInt())).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(GROUP_ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableGroupId() throws Exception {

    Mockito.when(menuGroupRepository.findByGroupId(Mockito.anyInt())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(GROUP_ID)
      .param("groupId", "111"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByName() throws Exception {

    Mockito.when(menuGroupRepository.findByName(Mockito.anyString())).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(NAME)
        .param("name", "Margherita"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Margherita"));

  }

  @Test
  public void getByMissingNameParameter() throws Exception {

    Mockito.when(menuGroupRepository.findByName(Mockito.anyString())).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(NAME))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableName() throws Exception {

    Mockito.when(menuGroupRepository.findByName(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(NAME)
      .param("name", "Margherita"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByGaenge() throws Exception {

    Mockito.when(menuGroupRepository.findByGaenge(Mockito.anyInt())).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(GAENGE)
        .param("gaenge", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Margherita"));

  }

  @Test
  public void getByMissingGaengeParameter() throws Exception {

    Mockito.when(menuGroupRepository.findByGaenge(Mockito.anyInt())).thenReturn(
        MenuGroupMockProvider.createMenuGroups());

    mockMvc.perform(get(GAENGE))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableGaenge() throws Exception {

    Mockito.when(menuGroupRepository.findByGaenge(Mockito.anyInt())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(GAENGE)
      .param("gaenge", "1"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

}
