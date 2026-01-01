# Multi-Threaded Job Scheduler

![Java](https://img.shields.io/badge/Java-17-orange)
![Concurrency](https://img.shields.io/badge/Concurrency-ExecutorService-blue)
![License](https://img.shields.io/badge/license-MIT-blue)

---

![Banner](https://raw.githubusercontent.com/aseemjan/multi-threaded-job-scheduler/main/src/main/assets/banner.png)

*A production-oriented job scheduling system built with Java, focusing on concurrency, correctness, and recovery.*

This project models a **real backend scheduling system**, emphasizing:
- safe concurrent execution
- clean object-oriented design
- restart resilience
- idempotent job execution
- observability through metrics and logs

Rather than a demo scheduler, the goal is to reflect how **production-grade backend systems** are designed and evolved incrementally.

---

## Key Features

- 🧵 **Multi-threaded execution engine**  
  Jobs are executed using `ExecutorService`–based thread pools with controlled parallelism.

- ⏱️ **Flexible scheduling model**
    - One-time jobs
    - Delayed jobs
    - Recurring jobs  
      (via pluggable execution strategies)

- 🧠 **OOP-driven architecture**
    - Strategy pattern for execution behavior
    - Factory pattern for strategy selection
    - Clear lifecycle boundaries for scheduling, execution, and recovery

- 💾 **Persistent job storage**
    - Jobs are stored via a persistence abstraction
    - Enables reload and rescheduling after application restarts

- 🔁 **Restart resilience**
    - Jobs in `SCHEDULED` or `RUNNING` state are recovered on restart
    - Interrupted jobs are safely handled and rescheduled

- 🔐 **Idempotent execution guard**
    - Execution ownership tokens prevent duplicate job execution
    - Guarantees at-most-once execution per job attempt, even during recovery

- 📊 **Observability-ready**
    - Structured lifecycle logs
    - Internal metrics counters for job states
    - Designed to be easily extended with monitoring systems

---

## Tech Stack

- Java 17
- Java Concurrency (`ExecutorService`, `ConcurrentHashMap`)
- Spring Boot (application bootstrap)
- MySQL (job persistence abstraction)
- JUnit 5 (testing)

---

## High-Level Architecture


```
Client / Bootstrap
↓
JobScheduler
↓
Execution Strategy (Strategy Pattern)
↓
ExecutorService (Thread Pool)
↓
JobStore (Persistence)
```

---

## Job Lifecycle Overview

1. Job is scheduled and persisted
2. Execution ownership is acquired using a token
3. Job is executed via the selected strategy
4. On success or failure:
    - execution ownership is released
    - metrics and logs are updated
5. On application restart:
    - recoverable jobs are reloaded and rescheduled safely

---

## Design Highlights

- Concurrency-first design with thread-safe data structures
- Clear separation of concerns:
    - scheduling
    - execution
    - persistence
    - recovery
- Restart-safe execution model
- Extensible core — new execution strategies can be added without modifying existing logic

---

## Testing Strategy

- Unit tests for core scheduling and execution paths
- In-memory job store used to validate recovery and idempotency behavior
- Controlled execution to reason about concurrency edge cases

---

## Future Enhancements

- Execution timeouts & stuck job detection
- Graceful shutdown handling
- Metrics export (Micrometer / Prometheus)
- Distributed execution using external queues
- Job analytics and dashboards

---

## Why This Project?

This project was built to demonstrate **practical backend engineering skills**, including:
- Java concurrency and thread-safety
- Application of design patterns
- Handling of failures and restarts
- Building systems that behave correctly under real-world conditions

---

## How to Run

```bash
mvn spring-boot:run

```
The application starts on:
http://localhost:8081