package com.info.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.info.entity.Ticket;
import com.info.sevice.TicketBookingService;

@RestController
public class TicketBookingController {

	@Autowired
	private TicketBookingService ticketBookingService;
	
	
	@PostMapping(value="/create")
	public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
		Ticket createTicket = ticketBookingService.createTicket(ticket);
		return new ResponseEntity<>(createTicket,HttpStatus.CREATED) ;
		
	}
	@GetMapping("/getAllTickets")
	public ResponseEntity<List<Ticket>> getAllTickets(){
		List<Ticket> allBookedTickets = ticketBookingService.getAllBookedTickets();
		return new ResponseEntity<List<Ticket>>(allBookedTickets,HttpStatus.OK);
		
		
	}
	@GetMapping(value="/ticketId/{ticketId}")
	public ResponseEntity<Ticket> getTicketById(@PathVariable("ticketId")Integer ticketId){
		Ticket ticketById = ticketBookingService.getTicketById(ticketId);
		return new ResponseEntity<Ticket>(ticketById,HttpStatus.OK);
	}
	@GetMapping(value="/email/{email:.+}")
	public ResponseEntity<Ticket> getTicketByEmail(@PathVariable("email")String email){
		Ticket ticketByEmail = ticketBookingService.getTicketByEmail(email);
		return new ResponseEntity<Ticket>(ticketByEmail,HttpStatus.OK);
	}
	@DeleteMapping(value="/ticketId/{ticketId}")
	public void deleteTicket(@PathVariable("ticketId")Integer ticketId){
		ticketBookingService.deleteTicketById(ticketId);
	}
	
	
	@PutMapping(value="/ticketId/{ticketId}/email/{newEmail:.+}")
	public ResponseEntity<Ticket> updateTicket(@PathVariable("ticketId")Integer ticketId,@PathVariable("newEmail")String newEmail){
		Ticket updateTicket = ticketBookingService.updateTicket(ticketId,newEmail);
		return new ResponseEntity<>(updateTicket,HttpStatus.OK);
	}

	
	

}

