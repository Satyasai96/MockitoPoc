package com.info.service.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.info.entity.Ticket;
import com.info.repository.TicketBookingRepository;
import com.info.sevice.TicketBookingService;

@SpringBootTest
public class TicketServiceTest {
	@Autowired
	private TicketBookingService ticketBookingService;
	@MockBean
	private TicketBookingRepository ticketBookingDao;

	@Test
	public void testCreateTicket() {
		Ticket ticket = new Ticket();
		ticket.setPassengerName("sai");
		ticket.setDestStation("kavali");
		ticket.setEmail("sai156@gmail.com");
		ticket.setSourceStation("hyd");
		ticket.setBookingDate(new Date());

		Mockito.when(ticketBookingDao.save(ticket)).thenReturn(ticket);
		assertThat(ticketBookingService.createTicket(ticket)).isEqualTo(ticket);
	}

	@Test
	public void testGetTicketById() {
		Ticket ticket = new Ticket();
		ticket.setTicketId(1);
		ticket.setPassengerName("sai");
		ticket.setDestStation("kavali");
		ticket.setEmail("sai156@gmail.com");
		ticket.setSourceStation("hyd");
		ticket.setBookingDate(new Date());

		Mockito.when(ticketBookingDao.findById(1)).thenReturn(Optional.of(ticket));
		assertThat(ticketBookingService.getTicketById(1)).isEqualTo(ticket);

	}

	@Test
	public void testGetAllTickets() {
		Ticket ticket1 = new Ticket();
		ticket1.setPassengerName("sai");
		ticket1.setDestStation("kavali");
		ticket1.setEmail("sai156@gmail.com");
		ticket1.setSourceStation("hyd");
		ticket1.setBookingDate(new Date());

		Ticket ticket2 = new Ticket();
		ticket2.setPassengerName("satya");
		ticket2.setDestStation("kavali");
		ticket2.setEmail("sai156@gmail.com");
		ticket2.setSourceStation("hyd");
		ticket2.setBookingDate(new Date());

		List<Ticket> ticketList = new ArrayList<>();
		ticketList.add(ticket2);
		ticketList.add(ticket2);

		Mockito.when(ticketBookingDao.findAll()).thenReturn(ticketList);
		assertThat(ticketBookingService.getAllBookedTickets()).isEqualTo(ticketList);

	}
	@Test
	public void testDeleteTicket() {
		Ticket ticket1 = new Ticket();
		ticket1.setPassengerName("sai");
		ticket1.setDestStation("kavali");
		ticket1.setEmail("sai156@gmail.com");
		ticket1.setSourceStation("hyd");
		ticket1.setBookingDate(new Date());
		ticketBookingService.deleteTicketById(ticket1.getTicketId());
		verify(ticketBookingDao,times(1)).deleteById(ticket1.getTicketId());

		
		
	}
	@Test
	public void testUpdate() {
		Ticket ticket1 = new Ticket();
		ticket1.setPassengerName("sai");
		ticket1.setDestStation("kavali");
		ticket1.setEmail("sai156@gmail.com");
		ticket1.setSourceStation("hyd");
		ticket1.setBookingDate(new Date());
		
		Mockito.when(ticketBookingDao.findById(1)).thenReturn(Optional.of(ticket1));
		ticket1.setEmail("ramu78@gmail.com");
		Mockito.when(ticketBookingDao.save(ticket1)).thenReturn(ticket1);
		assertThat(ticketBookingService.updateTicket(1, "sai156@gmail.com")).isEqualTo(ticket1);
		
		
	}
	@Test
	public void testGetByEmail() {
		Ticket ticket1 = new Ticket();
		ticket1.setPassengerName("sai");
		ticket1.setDestStation("kavali");
		ticket1.setEmail("sai156@gmail.com");
		ticket1.setSourceStation("hyd");
		ticket1.setBookingDate(new Date());
		Mockito.when(ticketBookingDao.findByEmail("sai156@gmail.com")).thenReturn(ticket1);
		assertThat(ticketBookingService.getTicketByEmail("sai156@gmail.com")).isEqualTo(ticket1);
		
		
	}

}
