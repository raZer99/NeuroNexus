package com.neuronexus.deployment_service.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/deploy")
public class DeploymentController {

    @PostMapping
    public String deploy(@RequestBody String env) {
        System.out.println("DEPLOYMENT SERVICE:");
        System.out.println("Deploying to: " + env);
        return "Deployment executed for environment: " + env;
    }
}
