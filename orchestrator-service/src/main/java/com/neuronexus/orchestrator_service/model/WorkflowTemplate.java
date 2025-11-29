package com.neuronexus.orchestrator_service.model;


import lombok.Data;

import java.util.List;

@Data
public class WorkflowTemplate {
    private String id;
    private List<WorkflowStep> steps;
    private Long tickerId;
}
