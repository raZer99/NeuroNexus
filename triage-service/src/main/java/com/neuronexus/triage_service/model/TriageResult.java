package com.neuronexus.triage_service.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TriageResult {
    private Long ticketId;
    private String priority;
    private String ownerTeam;
    private String reason;
}
