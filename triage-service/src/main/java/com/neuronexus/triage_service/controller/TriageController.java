package com.neuronexus.triage_service.controller;


import com.neuronexus.triage_service.model.TriageRequest;
import com.neuronexus.triage_service.model.TriageResult;
import com.neuronexus.triage_service.service.TriageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/triage")
public class TriageController {

    private final TriageService triageService;

    public TriageController(TriageService triageService) {
        this.triageService = triageService;
    }

    @PostMapping
    public TriageResult classify(@RequestBody TriageRequest request){
        return triageService.classify(request);
    }

    // optional quick health checkpoint
    @GetMapping("/health")
    public String health(){
        return "triage health: OK";
    }
}
