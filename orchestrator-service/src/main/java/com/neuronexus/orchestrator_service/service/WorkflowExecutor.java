package com.neuronexus.orchestrator_service.service;

import com.neuronexus.orchestrator_service.model.WorkflowStep;
import com.neuronexus.orchestrator_service.model.WorkflowTemplate;
import org.springframework.context.annotation.Bean;
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
        System.out.println("\n Starting workflow: " +template.getId());

        for(WorkflowStep step : template.getSteps()) {
            switch(step.getType()) {

                case "notify":
                    System.out.println("Calling notification-service...");
                    Map<String, Object> notifyPayload = new HashMap<>();
                    notifyPayload.put("message", step.getMessage());

                    String notifyResp = restTemplate.postForObject(
                            "http://localhost:8083/notify",
                            notifyPayload,
                            String.class
                    );

                    System.out.println("Notification-service responded: " + notifyResp);
                    break;

                case "deployment":
                    System.out.println(" Calling deployment-service...");
                    Map<String, Object> deployPayload = new HashMap<>();
                    deployPayload.put("target", step.getType());

                    String deployResp = restTemplate.postForObject(
                            "http://localhost:8084/deploy",
                            deployPayload,
                            String.class
                    );
                    System.out.println(" Deployment-service responded: " + deployResp);
                    break;

                case "log":
                    System.out.println(" LOG: " + step.getMessage());
                    break;

                case "complete":
                    System.out.println(" COMPLETED: " + step.getMessage());
                    break;

                default:
                    System.out.println(" Unknown step type: " + step.getType());
            }
        }

        System.out.println("Workflow execution finished!\n");
    }
}
