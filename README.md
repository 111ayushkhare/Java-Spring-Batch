# Spring Batch Master Hub

[![Java 21](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Batch](https://img.shields.io/badge/Spring%20Batch-5.x-green.svg)](https://spring.io/projects/spring-batch)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue.svg)](https://www.postgresql.org/)

An repository hub showcasing modular, production-ready **Spring Batch** reference architectures and real-world ETL patterns.

This repository serves as the central parent workspace for Spring Batch projects—each structured as a standalone, runnable micro-application demonstrating specific batch processing capabilities. Newer batch processing projects will be continuously added here as individual modules.

---

## Projects Catalog

| Project | Description | Core Patterns & Components | Status | Release |
| :--- | :--- | :--- | :---: | :---: |
| **[batch-one](batch-one/README.md)** | **Employee Salary Batch Pipeline** | • `JdbcCursorItemReader` streaming<br/>• Business transformation & threshold filter<br/>• `JdbcBatchItemWriter` batch inserts<br/>• AspectJ AOP execution monitoring (`LoggingAspect`)<br/>• PostgreSQL persistence & auto-DDL | `Ready` | [`v0.1.0`](https://github.com/111ayushkhare/Java-Spring-Batch---1/releases/tag/v0.1.0) |
| **[batch2csv](batch2csv/README.md)** | **Dynamic CSV to Database Pipeline** | • `FlatFileItemReader` for dynamic CSV parsing<br/>• REST API triggering (`@RestController`)<br/>• Comprehensive lifecycle tracking (`JobListener`, `StepListener`, `ChunkListener`)<br/>• `JdbcBatchItemWriter` bulk inserts<br/>• Global exception handling | `Ready` | `WIP` |

> Newer batch projects demonstrating patterns such as flat-file ingestion, skip/retry fault tolerance, partitioned processing, and event-driven architectures will be added as sibling modules in this hub.

---

## Repository Structure

```
spring-batch-1/
├── .gitignore
├── README.md
├── batch-one/
└── batch2csv/
```

---

## Technology Stack

- **Runtime & Language**: Java 21 (LTS)
- **Frameworks**: Spring Boot 4.x, Spring Batch 5.x
- **Data Persistence**: Spring Data JDBC, PostgreSQL 16+
- **Cross-Cutting Concerns**: Spring AOP, AspectJ Weaver
- **Utilities**: Project Lombok
- **Build & Dependency Management**: Apache Maven (Wrapper included with each module)
- **Containerization**: Docker (PostgreSQL instances)

---

## Getting Started

### 1. Prerequisites
- **JDK 21** or later installed and configured (`JAVA_HOME`)
- **Docker** or a local **PostgreSQL** instance
- **Git**

### 2. Start PostgreSQL
Launch a shared PostgreSQL container for development:

```bash
docker run --name batch-postgres \
  -e POSTGRES_DB=batchdb \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:16-alpine
```

### 3. Launch a Batch Project
Each batch project is an independent Maven module. To run a project, navigate to its directory:

```bash
# Navigate to project
cd batch-one

# Copy environment template
cp .env.example .env

# Run using the Maven wrapper
./mvnw spring-boot:run
```

Refer to the project's internal [README](batch-one/README.md) for detailed architectural documentation, database schemas, and configuration options.
