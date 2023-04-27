package com.info.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.info.entity.Ticket;
import com.info.repository.TicketBookingRepository;

@Service
public class TicketBookingService {

	@Autowired
	private TicketBookingRepository repo;

	public Ticket createTicket(Ticket ticket) {
		return repo.save(ticket);
	}

	public  Ticket getTicketById(Integer ticketId) {
		return repo.findById(ticketId).get();
	}

	public List<Ticket> getAllBookedTickets() {

		return repo.findAll();
	}

	public void deleteTicketById(Integer ticketId) {
		repo.deleteById(ticketId);
	}

	public Ticket updateTicket(Integer ticketId, String newEmail) {
		Ticket ticketFromDb = repo.findById(ticketId).get();
		ticketFromDb.setEmail(newEmail);
		Ticket upadedTicket = repo.save(ticketFromDb);
		return upadedTicket;
	}

	public Ticket getTicketByEmail(String email) {
		return repo.findByEmail(email);
	}
}
