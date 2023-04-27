package com.info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.info.entity.Ticket;

public interface TicketBookingRepository extends JpaRepository<Ticket, Integer> {

	Ticket findByEmail(String email);

}