package com.neuronexus.ticket_service.repository;

import com.neuronexus.ticket_service.model.WorkflowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRecordRepository extends JpaRepository<WorkflowRecord, Long> {
}
