package com.neuronexus.jira_service.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jira")
public class JiraController {

    @PostMapping("/create")
    public String createJiraTicket(@RequestBody JiraRequest request){
        System.out.println("------ JIRA SERVICE ------");
        System.out.println("Creating JIRA Issue:");
        System.out.println("Title: " + request.getTitle());
        System.out.println("Description: " + request.getDescription());

        return "JIRA request created successfully for: " + request.getTitle();

    }

}

@Data
class JiraRequest{
    private String title;
    private String description;
}