package com.neuronexus.triage_service.model;

import lombok.Data;

@Data
public class TriageRequest {
    private Long ticketId;
    private String title;
    private String description;
}
