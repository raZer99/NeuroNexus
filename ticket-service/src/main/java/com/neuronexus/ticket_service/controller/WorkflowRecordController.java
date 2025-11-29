package com.neuronexus.ticket_service.controller;

import com.neuronexus.ticket_service.model.WorkflowRecord;
import com.neuronexus.ticket_service.repository.WorkflowRecordRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class WorkflowRecordController {

    @Autowired
    private WorkflowRecordRepository repo;

    @PostMapping("/save")
    public String save(@RequestBody WorkflowRecord record){
        repo.save(record);
        return "Workflow record saved";
    }

}


