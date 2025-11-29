package com.neuronexus.orchestrator_service.service;

import com.neuronexus.orchestrator_service.model.WorkflowStep;
import com.neuronexus.orchestrator_service.model.WorkflowTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class WorkflowExecutor {

    private final RestTemplate restTemplate;

    public WorkflowExecutor(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void execute(WorkflowTemplate template) {

        System.out.println("\nStarting workflow: " + template.getId() + "\n");

        for (WorkflowStep step : template.getSteps()) {
            switch (step.getType()) {

                case "notify":
                    System.out.println("Calling notification-service...");
                    Map<String, Object> notifyPayload = new HashMap<>();
                    notifyPayload.put("message", step.getMessage());

                    String notifyResp = restTemplate.postForObject(
                            "http://localhost:8083/notify",
                            notifyPayload,
                            String.class
                    );

                    System.out.println("Notification-service responded: " + notifyResp + "\n");
                    break;

                case "jira":
                    System.out.println("Calling jira-service...");
                    Map<String, Object> jiraPayload = new HashMap<>();
                    jiraPayload.put("title", "Incident: " + template.getId());
                    jiraPayload.put("description", step.getMessage());

                    String jiraResp = restTemplate.postForObject(
                            "http://localhost:8085/jira/create",
                            jiraPayload,
                            String.class
                    );

                    System.out.println("Jira-service responded: " + jiraResp + "\n");
                    break;

                case "deployment":
                    System.out.println("Calling deployment-service...");
                    Map<String, Object> deployPayload = new HashMap<>();
                    deployPayload.put("target", step.getMessage());

                    String deployResp = restTemplate.postForObject(
                            "http://localhost:8084/deploy",
                            deployPayload,
                            String.class
                    );

                    System.out.println("Deployment-service responded: " + deployResp + "\n");
                    break;

                case "log":
                    System.out.println("LOG: " + step.getMessage() + "\n");
                    break;

                case "complete":
                    System.out.println("COMPLETED: " + step.getMessage() + "\n");
                    break;

                default:
                    System.out.println("Unknown step type: " + step.getType() + "\n");
            }
        }

        System.out.println("Workflow execution finished!\n");

        // ---- Save workflow record into DB ----
        Map<String, Object> recordPayload = new HashMap<>();
        recordPayload.put("ticketId", template.getId());
        recordPayload.put("workflowName", template.getId());
        recordPayload.put("status", "COMPLETED");
        recordPayload.put("timestamp", java.time.LocalDateTime.now().toString());

        String saveResp = restTemplate.postForObject(
                "http://localhost:8080/workflow/save",
                recordPayload,
                String.class
        );

        System.out.println("DB Save Response: " + saveResp + "\n");
    }
}
