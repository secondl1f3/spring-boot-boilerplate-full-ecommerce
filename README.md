# Java Spring Boot Boilerplate for E-Commerce Services (Spring Boot 3.1)

I still maintain this project by adding more features.

Upcoming features:
1. Integration with Payment Gateway
2. Integration with Logistic/Shipment Company
3. Etc..

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](https://opensource.org/licenses/MIT)

### Included technologies
- Spring Boot 3.1
- Spring Security
- Spring Data JPA
- Spring Data Redis
- Hibernate
- JWT Authentication
- PostgreSQL
- Redis
- Liquibase
- Lombok
- Swagger
- JUnit 5
- Mockito

### Requirements
- Java 17
- Maven 3.9.3
- Docker 20.10.8
- Docker Compose 2.19.1
- PostgreSQL 13.11
- Redis 7.0.12

### Run with Docker Compose
```bash
docker-compose up --build -d
```

### Install dependencies
```bash
mvn clean install
```

### Run project
```bash
mvn spring-boot:run 
```

### Build project
```bash
mvn clean package
```

### Skip integration tests
```bash
mvn clean install -DskipITs=true
```
