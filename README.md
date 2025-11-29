# 🚀 NeuroNexus — AI-Powered Workflow Orchestration Platform

**NeuroNexus** is a microservices-based automation platform that orchestrates IT workflows end-to-end — from ticket creation and triage to notifications, JIRA creation, deployments, and audit logging.  
Built using **Spring Boot**, **PostgreSQL**, **Docker**, and clean workflow-driven design.

---

## 🎯 Objectives
- Automate repetitive IT operations such as triage, notifications, and deployments.
- Provide a scalable orchestration system using microservices.
- Build a real production-style backend project.
- Demonstrate clean architecture, REST integration, async workflows, and DB auditing.

---

## 🧩 System Architecture (Planned + Partially Completed)

### **1. Ticket Service (8080)**
- Accepts user-submitted tickets
- Stores ticket data in PostgreSQL
- Triggers the Triage + Orchestrator pipeline

### **2. Triage Service (8081)**
- Reads ticket description
- Applies keyword-based "AI-like" logic
- Returns:
    - Priority (P1/P2/P3)
    - Owner team (devops / db / network / auth)
    - Reason for classification

### **3. Orchestrator Service (8082)**
Executes workflow templates step-by-step:
- Notify team
- Create JIRA (mock)
- Trigger deployments
- Add logs
- Save workflow completion record to DB

### **4. Action Services**
- **Notification Service (8083)** — mock team notifications
- **Deployment Service (8084)** — mock CI/CD execution
- **JIRA Service (8085)** — mock JIRA ticket creation

### **5. Audit Database**
Stores:
- Created tickets
- Completed workflows
- Workflow status/history

---

## 🛠 Tech Stack
- **Java 21**
- **Spring Boot 3**
- **Spring Web / JPA / Validation**
- **PostgreSQL + Docker Compose**
- **Lombok**
- **RestTemplate-based microservice communication**
- (🔮 **AI planned** — triage will later be replaced with LLM classification)

---

## 📦 How It Works (Simple Explanation)

1️⃣ **User sends a ticket**

```ansi
[96m               +----------------------+[0m
[96m               |   User / Postman     |[0m
[96m               |  creates a Ticket    |[0m
[96m               +----------+-----------+[0m
                           |
                           v
[94m               +-----------------------+[0m
[94m               |   Ticket Service      |[0m
[94m               |  stores ticket +      |[0m
[94m               |  calls triage         |[0m
[94m               +-----------+-----------+[0m
                            |
                            v
[92m               +-----------------------+[0m
[92m               |    Triage Service     |[0m
[92m               |  priority + owner     |[0m
[92m               |  team detection       |[0m
[92m               +-----------+-----------+[0m
                            |
                            v
[93m               +-----------------------+[0m
[93m               |     Orchestrator      |[0m
[93m               |  Runs workflow steps: |[0m
[93m               |   - notify team       |[0m
[93m               |   - create JIRA       |[0m
[93m               |   - trigger deploy    |[0m
[93m               |   - logs + complete   |[0m
[93m               +-----------+-----------+[0m
                            |
     ----------------------------------------------------------------------------------------
     |                 |                   |                 |                              |
     v                 v                   v                 v                              v
[95m+----------------+[0m [95m+----------------+[0m [95m+----------------+[0m [95m+----------------+[0m
[95m| Notification   |[0m [95m|    JIRA         |[0m [95m| Deployment     |[0m [95m|  Ticket DB     |[0m
[95m|    Service     |[0m [95m|    Service      |[0m [95m|    Service     |[0m [95m| Workflow DB    |[0m
[95m+----------------+[0m [95m+----------------+[0m [95m+----------------+[0m [95m+----------------+[0m





