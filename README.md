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
`POST /tickets`
```json
{
  "title": "Deploy request",
  "description": "please deploy urgent build :::"
}

                +----------------------+
                |   User / Postman     |
                |  creates a Ticket    |
                +----------+-----------+
                           |
                           v
                +-----------------------+
                |   Ticket Service      |
                |  (stores ticket +     |
                |   calls triage)       |
                +-----------+-----------+
                            |
                            v
                +-----------------------+
                |    Triage Service     |
                |  (priority + owner    |
                |   team detection)     |
                +-----------+-----------+
                            |
                            v
                +-----------------------+
                |   Orchestrator        |
                |  Runs workflow steps: |
                |  - notify team        |
                |  - create JIRA        |
                |  - trigger deploy     |
                |  - logs + complete    |
                +-----------+-----------+
                            |
      -------------------------------------------------
      |                  |                 |          |
      v                  v                 v          v
+----------------+ +----------------+ +----------------+ +----------------+
| Notification |        | JIRA |        | Deployment |      | Ticket DB |
| Service |           | Service |        | Service |       | Workflow DB |
+----------------+ +----------------+ +----------------+ +----------------+


------------------------------------------------------------------------------------------------------------------------





