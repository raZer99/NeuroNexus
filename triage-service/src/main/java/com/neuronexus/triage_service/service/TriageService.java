package com.neuronexus.triage_service.service;


import com.neuronexus.triage_service.model.TriageRequest;
import com.neuronexus.triage_service.model.TriageResult;
import org.springframework.stereotype.Service;

@Service
public class TriageService {

    public TriageResult classify(TriageRequest request) {

        System.out.println("\nTRIAGE SERVICE RECEIVED REQUEST:");
        System.out.println("Ticket ID: " + request.getTicketId());
        System.out.println("Title: " + request.getTitle());
        System.out.println("Description: " + request.getDescription());
        System.out.println("-------------------------------------");

        String desc =  request.getDescription() == null ? "" : request.getDescription().toLowerCase();
        String priority = "P3";
        String owner = "general-support";
        String reason = "Default classification";

        if(desc.contains("down") || desc.contains("urgent") || desc.contains("critical")){
            priority = "P1";
            reason = "Critical Keyword found";
        } else if (desc.contains("error") || desc.contains("issue") || desc.contains("exception")) {
            priority = "P2";
            reason = "Issue-related keyword found";
        }


        if (desc.contains("db")) owner = "db-team";
        else if (desc.contains("network")) owner = "network-team";
        else if (desc.contains("login") || desc.contains("auth")) owner = "auth-team";
        else if (desc.contains("deploy") || desc.contains("ci")) owner = "devops-team";

        TriageResult result = TriageResult.builder()
                .ticketId(request.getTicketId())
                .priority(priority)
                .ownerTeam(owner)
                .reason(reason)
                .build();

        System.out.println("TRIAGE RESULT:");
        System.out.println(result);
        System.out.println("-------------------------------------\n");

        return result;
    }
}
