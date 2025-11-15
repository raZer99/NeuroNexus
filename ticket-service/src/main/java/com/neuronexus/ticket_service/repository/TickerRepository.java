package com.neuronexus.ticket_service.repository;

import com.neuronexus.ticket_service.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TickerRepository extends JpaRepository<Ticket, Long> {

}
