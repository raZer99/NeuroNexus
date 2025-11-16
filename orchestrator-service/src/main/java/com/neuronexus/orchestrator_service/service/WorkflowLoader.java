package com.neuronexus.orchestrator_service.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.neuronexus.orchestrator_service.model.WorkflowTemplate;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import com.neuronexus.orchestrator_service.model.WorkflowTemplate;

@Service
public class WorkflowLoader {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper mapper;

    public WorkflowLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        this.mapper = new ObjectMapper(new YAMLFactory());
    }

    public WorkflowTemplate loadTemplate(String id) {
        try {
            Resource resource = resourceLoader.getResource("classpath:workflows/" + id + ".yml");
            return mapper.readValue(resource.getInputStream(), WorkflowTemplate.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load workflow template " + id, e);
        }
    }
}
