# Quiz App - Microservices Architecture
## Overview
This Quiz App is developed using a Microservices Architecture style, leveraging various Spring Boot features and technologies. The application is divided into multiple microservices, each serving a specific purpose and communicating with each other seamlessly.

### Microservices:
The core functionality is implemented as microservices, providing modular and independent services. Each microservice is responsible for a specific aspect of the application.

### Eureka Server:
The Eureka Server acts as a service registry where microservices register themselves, enabling dynamic service discovery.

### Eureka Client:
Microservices act as Eureka Clients, registering with the Eureka Server for service discovery and communication.

### API Gateway:
The API Gateway serves as the entry point for external clients, routing requests to the appropriate microservices. It helps to centralize and manage the communication flow.

### FeignClient Interfaces:
FeignClients provide a declarative way to make HTTP requests to other microservices, simplifying inter-service communication.

### PostgreSQL & JPA:
Microservices use PostgreSQL as the database, and JPA for seamless data persistence and retrieval.

## How to Run
- Start the Eureka Server.
- Run each microservice independently.
- Start the API Gateway.
- Ensure that the dependencies are resolved and configurations are appropriately set in each microservice.

## Technologies Used
- Spring Boot
- Eureka Server & Eureka Client
- API Gateway
- FeignClient
- PostgreSQL & JPA
