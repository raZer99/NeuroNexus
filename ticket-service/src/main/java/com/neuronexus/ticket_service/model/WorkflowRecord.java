package com.neuronexus.ticket_service.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class WorkflowRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ticketId;
    private String workflowName;
    private String status;
    private String timestamp;
}
