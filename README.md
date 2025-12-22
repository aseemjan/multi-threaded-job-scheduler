# Multi-Threaded Job Scheduler

![Java](https://img.shields.io/badge/Java-17-orange)
![Concurrency](https://img.shields.io/badge/Concurrency-ExecutorService-blue)
![Spring Boot](https://img.shields.io/badge/Spring-Boot-brightgreen)
![Database](https://img.shields.io/badge/Database-MySQL-blue)
![License](https://img.shields.io/badge/license-MIT-blue)

---

![Banner](https://raw.githubusercontent.com/aseemjan/multi-threaded-job-scheduler/main/src/main/assets/banner.png)

*A production-oriented job scheduling system built with Java and Spring.*

This project focuses on building a **concurrency-safe, extensible job scheduler** capable of executing  
**one-time, delayed, recurring, and priority-based jobs** using custom thread pools.

Instead of being a simple scheduler demo, the goal here is to model **real backend systems** —
clean architecture, solid OOP design, safe concurrency, restart resilience, and observability.

---

## Key Features

- 🧵 **Multi-threaded execution engine**  
  Custom `ExecutorService`–based thread pools with controlled parallelism

- ⏱️ **Flexible scheduling**
    - One-time jobs
    - Delayed jobs
    - Recurring jobs
    - Priority-based execution

- 🧠 **OOP-driven design**
    - Strategy pattern for execution policies
    - Factory pattern for job creation
    - Template Method for job lifecycle control

- 💾 **Persistent job storage**
    - MySQL-backed job metadata
    - Automatic recovery and rescheduling on application restart

- 🔁 **Resilience & fault tolerance**
    - Safe handling of in-flight and pending jobs
    - Sub-2 second recovery for persisted jobs

- 📊 **Observability-ready**
    - Structured logging
    - Health endpoints
    - Metrics-friendly design (Micrometer-ready)

---

## Tech Stack

- Java 17
- Spring Boot
- Java Concurrency (`ExecutorService`, `BlockingQueue`)
- MySQL (job persistence)
- Spring Data JPA
- Flyway (schema migrations)
- SLF4J / Logback
- JUnit 5

---

## High-Level Architecture

Client
↓
Scheduler API
↓
Job Dispatcher
↓
Execution Strategy (Strategy Pattern)
↓
Thread Pool (ExecutorService)
↓
Job Persistence (MySQL)

---

## Job Types Supported
-One-time jobs
-Execute immediately or at a fixed timestamp
-Delayed jobs
-Execute after a configured delay
-Recurring jobs
-Execute repeatedly at a fixed interval
-Priority jobs
-Higher priority jobs are executed before lower priority ones

--- 

## Design Highlights
-Concurrency-first approach
-Thread-safe queues and clear execution boundaries to avoid race conditions
-Separation of concerns
-Scheduling, execution, persistence, and recovery are cleanly decoupled
-Restart resilience
-Pending jobs are reloaded and rescheduled automatically on startup
-Extensible core
-New job types or execution strategies can be added without modifying existing logic

---

## Configuration
-Key scheduler properties:

scheduler.thread.pool.size=10
scheduler.retry.enabled=true
scheduler.recovery.enabled=true

-Located in:
src/main/resources/application.properties

---

## Testing Strategy
-Unit tests for scheduling and execution logic
-Controlled executor usage for concurrency edge cases
-Persistence logic tested independently

---

## Future Improvements
-Redis-backed distributed queue
-Micrometer metrics with Prometheus/Grafana
-Docker Compose setup (App + MySQL)
-Retry & exponential backoff strategies
-Job execution analytics and dashboards
-Distributed scheduling with leader election
-Load and stress testing

---

## Why This Project?
-This project is designed to demonstrate real backend engineering depth:
-Strong understanding of Java concurrency
-Practical application of SOLID principles & design patterns
-Handling of failure scenarios, restarts, and recovery
-Architecture that scales beyond a single JVM

---

## How to Run

```bash
mvn spring-boot:run

The application starts on:

http://localhost:8081