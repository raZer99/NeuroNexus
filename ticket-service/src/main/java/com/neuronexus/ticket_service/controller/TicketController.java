package com.neuronexus.ticket_service.controller;


import com.neuronexus.ticket_service.model.Ticket;
import com.neuronexus.ticket_service.repository.TickerRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TickerRepository repo;

    public TicketController(TickerRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        ticket.setStatus("CREATED");
        ticket.setCreatedAt(LocalDateTime.now());
        return repo.save(ticket);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }
}
