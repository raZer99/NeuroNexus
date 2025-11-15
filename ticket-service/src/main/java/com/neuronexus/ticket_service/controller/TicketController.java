package com.neuronexus.ticket_service.controller;


import com.neuronexus.ticket_service.model.Ticket;
import com.neuronexus.ticket_service.model.TriageResult;
import com.neuronexus.ticket_service.repository.TickerRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final RestTemplate restTemplate;

    private final TickerRepository repo;

    public TicketController(TickerRepository repo, RestTemplate restTemplate) {
        this.repo = repo;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {

        // 1. Save ticket
        ticket.setStatus("CREATED");
        ticket.setCreatedAt(LocalDateTime.now());
        Ticket saved = repo.save(ticket);

        Map<String, Object> req = new HashMap<>();
        req.put("ticketId", saved.getId());
        req.put("title", saved.getTitle());
        req.put("description", saved.getDescription());

        //3. Call triage-service
        String TRIAGE_URL = "http://localhost:8081/triage";
        TriageResult result = restTemplate.postForObject(
                TRIAGE_URL,
                req,
                TriageResult.class
        );

        // 4. Log the triage result
        System.out.println("\n----------------------------");
        System.out.println("TRIAGE RESULT RECEIVED:");
        System.out.println(result);
        System.out.println("----------------------------\n");

        return saved;
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
