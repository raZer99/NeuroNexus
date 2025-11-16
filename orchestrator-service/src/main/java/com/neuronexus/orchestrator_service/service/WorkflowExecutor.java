package com.neuronexus.orchestrator_service.service;

import com.neuronexus.orchestrator_service.model.WorkflowStep;
import com.neuronexus.orchestrator_service.model.WorkflowTemplate;
import org.springframework.stereotype.Service;

@Service
public class WorkflowExecutor {

    public void execute(WorkflowTemplate template) {
        System.out.println("\n🔁 Starting workflow: " + template.getId());

        for(WorkflowStep step : template.getSteps()) {
            switch(step.getType()) {
                case "notify":
                    System.out.println("📢 NOTIFY: " + step.getMessage());
                    break;

                case "log":
                    System.out.println("📝 LOG: " + step.getMessage());
                    break;

                case "complete":
                    System.out.println("✅ DONE: " + step.getMessage());
                    break;

                default:
                    System.out.println("⚠ Unknown step type: " + step.getType());
            }
        }
        System.out.println("🎉 Workflow execution finished!\n");
    }
}
