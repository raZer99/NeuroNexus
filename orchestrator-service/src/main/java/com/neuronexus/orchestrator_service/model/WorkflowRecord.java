package com.neuronexus.orchestrator_service.model;

import lombok.Data;

@Data
public class WorkflowRecord {
    private Long ticketId;
    private String workflowName;
    private String status;
    private String timestamp;
}
