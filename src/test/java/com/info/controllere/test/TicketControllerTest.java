package com.info.controllere.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.info.entity.Ticket;
import com.info.sevice.TicketBookingService;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TicketControllerTest {
	@MockBean
	private TicketBookingService service;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllBookedTickets() throws Exception {

		Ticket mockTicket1 = new Ticket();
		mockTicket1.setTicketId(1);
		mockTicket1.setPassengerName("satyasai");
		mockTicket1.setSourceStation("hyd");
		mockTicket1.setDestStation("ongole");
		mockTicket1.setBookingDate(new Date());
		mockTicket1.setEmail("sai123@gmail.com");

		Ticket mockTicket2 = new Ticket();
		mockTicket2.setPassengerName("mani");
		mockTicket2.setSourceStation("nellore");
		mockTicket2.setDestStation("chennai");
		mockTicket2.setBookingDate(new Date());
		mockTicket2.setEmail("mani234@gmail.com");

		List<Ticket> ticketList = new ArrayList<>();
		ticketList.add(mockTicket1);
		ticketList.add(mockTicket2);

		Mockito.when(service.getAllBookedTickets()).thenReturn(ticketList);

		String URI = "/getAllTickets";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response.getContentAsString());

	}

	@Test
	public void testGetTicketById() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(1);
		mockTicket.setPassengerName("satyasai");
		mockTicket.setSourceStation("hyd");
		mockTicket.setDestStation("ongole");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("sai123@gmail.com");

		Mockito.when(service.getTicketById(Mockito.anyInt())).thenReturn(mockTicket);

		String URI = "/ticketId/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response.getContentAsString());
	}

	@Test
	public void createTicketTest() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(1);
		mockTicket.setPassengerName("satyasai");
		mockTicket.setSourceStation("hyd");
		mockTicket.setDestStation("ongole");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("sai123@gmail.com");

		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(mockTicket);
		Mockito.when(service.createTicket(Mockito.any(Ticket.class))).thenReturn(mockTicket);
		String URI = "/create";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(userJson).contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response.getContentAsString());

	}

	@Test
	public void testGetTicketByEmail() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(1);
		mockTicket.setPassengerName("satyasai");
		mockTicket.setSourceStation("hyd");
		mockTicket.setDestStation("ongole");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("sai123@gmail.com");

		Mockito.when(service.getTicketByEmail(Mockito.anyString())).thenReturn(mockTicket);

		String URI = "/email/sai123@gmail.com";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response.getContentAsString());

	}

	@Test
	public void testUpdateTicket() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(1);
		mockTicket.setPassengerName("satyasai");
		mockTicket.setSourceStation("hyd");
		mockTicket.setDestStation("ongole");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("sai123@gmail.com");

		Mockito.when(service.updateTicket(Mockito.anyInt(), Mockito.anyString())).thenReturn(mockTicket);

		String URI = "/ticketId/1/email/sai123@gmail.com";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response.getContentAsString());

	}
	@Test
	public void testDeleteTicket() throws Exception {
		Ticket mockTicket = new Ticket();
		mockTicket.setTicketId(1);
		mockTicket.setPassengerName("satyasai");
		mockTicket.setSourceStation("hyd");
		mockTicket.setDestStation("ongole");
		mockTicket.setBookingDate(new Date());
		mockTicket.setEmail("sai123@gmail.com");
		
		int ticketId=1;
		Mockito.when(service.getTicketById(ticketId)).thenReturn(mockTicket);
		
		String URI = "/ticketId/1";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertNotNull(response.getContentAsString());


		
		
		
	}

}
