package com.neuronexus.orchestrator_service.controller;

import com.neuronexus.orchestrator_service.model.WorkflowTemplate;
import com.neuronexus.orchestrator_service.service.WorkflowExecutor;
import com.neuronexus.orchestrator_service.service.WorkflowLoader;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orchestrate")
public class OrchestratorController {

    private final WorkflowLoader loader;
    private final WorkflowExecutor executor;

    public OrchestratorController(WorkflowLoader loader, WorkflowExecutor executor) {
        this.loader = loader;
        this.executor = executor;
    }

    @PostMapping("/start")
    public String startWorkflow(@RequestParam String workflow) {
        WorkflowTemplate template = loader.loadTemplate(workflow);
        executor.execute(template);
        return "Workflow '" + workflow + "' exected successfully!";
    }
}
