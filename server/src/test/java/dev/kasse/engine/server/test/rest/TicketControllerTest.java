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

import dev.kasse.engine.server.repository.query.TicketRepository;
import dev.kasse.engine.server.service.TicketService;
import dev.kasse.engine.server.state.PaymentType;
import dev.kasse.engine.server.state.TicketState;
import dev.kasse.engine.server.test.AbstractTest.AbstractTest;
import dev.kasse.engine.server.test.MockProvider.TicketMockProvider;

/**
 * 
 * @author Deivarayan Azhagappan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mongo-repository-context.xml")
@WebAppConfiguration
public class TicketControllerTest extends AbstractTest {

  public static final String PATH = "/ticket";

  public static final String ALL = PATH + "/all";

  public static final String ID = PATH + "/id";

  public static final String STATE = PATH + "/state";

  public static final String PAYMENT_TYPE = PATH + "/paymentType";

  public static final String TABLE_NUMBER = PATH + "/tableNumber";

  @Autowired
  private WebApplicationContext ctx;

  private MockMvc mockMvc;

  @Autowired
  @InjectMocks
  private TicketService ticketService;

  @Mock
  private TicketRepository ticketRepository;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    ticketService.setTicketRepository(ticketRepository);
  }

  @Test
  public void getAllTickets() throws Exception {

    Mockito.when(ticketRepository.findAll()).thenReturn(
        TicketMockProvider.createTickets());

    mockMvc.perform(get(ALL)).andExpect(status().isOk())
        .andExpect(jsonPath("$.*", hasSize(1)))
        .andExpect(jsonPath("$[0].ticketItems", hasSize(2)));
  }


  @Test
  public void getById() throws Exception {

    Mockito.when(ticketRepository.findById("kasseId")).thenReturn(
        TicketMockProvider.createNewTicket(1));

    mockMvc.perform(get(ID).param("id", "kasseId"))
        .andExpect(status().isOk()).andExpect(jsonPath("id").value("kasseId"));

  }

  @Test
  public void getByMissingIdParameter() throws Exception {

    Mockito.when(ticketRepository.findById("kasseId")).thenReturn(
        TicketMockProvider.createNewTicket(1));

    mockMvc.perform(get(ID))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableId() throws Exception {

    Mockito.when(ticketRepository.findById(Mockito.anyString())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(ID).param("id", "kasseId"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByState() throws Exception {

    Mockito.when(ticketRepository.findByTicketState(TicketState.OPEN)).thenReturn(
        TicketMockProvider.createTickets());

    mockMvc.perform(get(STATE)
        .param("ticketState", TicketState.OPEN.name()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].ticketState").value(TicketState.OPEN.name()));

  }

  @Test
  public void getByMissingStateParameter() throws Exception {

    Mockito.when(ticketRepository.findByTicketState(TicketState.OPEN)).thenReturn(
        TicketMockProvider.createTickets());

    mockMvc.perform(get(STATE))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableState() throws Exception {

    Mockito.when(ticketRepository.findByTicketState(TicketState.OPEN)).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(STATE).param("ticketState", TicketState.OPEN.name()))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByPaymentType() throws Exception {

    Mockito.when(ticketRepository.findByPaymentType(PaymentType.CASH)).thenReturn(
        TicketMockProvider.createTickets());

    mockMvc.perform(get(PAYMENT_TYPE)
        .param("paymentType", PaymentType.CASH.name()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].paymentType").value(PaymentType.CASH.name()));

  }

  @Test
  public void getByMissingPaymentTypeParameter() throws Exception {

    Mockito.when(ticketRepository.findByPaymentType(PaymentType.CASH)).thenReturn(
        TicketMockProvider.createTickets());

    mockMvc.perform(get(PAYMENT_TYPE))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailablePaymentType() throws Exception {

    Mockito.when(ticketRepository.findByPaymentType(PaymentType.CASH)).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(PAYMENT_TYPE)
      .param("paymentType", PaymentType.CASH.name()))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

  @Test
  public void getByTableNumber() throws Exception {

    Mockito.when(ticketRepository.findByTableNumber(Mockito.anyInt())).thenReturn(
        TicketMockProvider.createTickets());

    mockMvc.perform(get(TABLE_NUMBER)
        .param("tableNumber", "1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].table.number").value(1));

  }

  @Test
  public void getByMissingTableNumberParameter() throws Exception {

    Mockito.when(ticketRepository.findByTableNumber(Mockito.anyInt())).thenReturn(
        TicketMockProvider.createTickets());

    mockMvc.perform(get(TABLE_NUMBER))
      .andExpect(status().is4xxClientError());

  }

  @Test
  public void getByUnavailableTableNumber() throws Exception {

    Mockito.when(ticketRepository.findByTableNumber(Mockito.anyInt())).thenReturn(
        null);

    MvcResult result = mockMvc.perform(get(TABLE_NUMBER)
      .param("tableNumber", "1"))
      .andExpect(status().isOk())
      .andReturn();

    assertTrue(result.getResponse().getContentAsString().isEmpty());

  }

}
