package com.neuronexus.ticket_service.model;

import lombok.Data;

@Data
public class TriageResult {
    private Long ticketId;
    private String priority;
    private String ownerTeam;
    private String reason;

    @Override
    public String toString() {
        return "TriageResult{" +
                "ticketId=" + ticketId +
                ", priority='" + priority + '\'' +
                ", ownerTeam='" + ownerTeam + '\'' +
                ", reason='" + reason + '\'' +
                '}';

    }
}
