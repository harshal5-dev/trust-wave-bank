# 🏦 Trust Wave Bank

> A modern, cloud-native banking application built with microservices architecture

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Docker](https://img.shields.io/badge/Docker-Enabled-blue.svg)](https://www.docker.com/)
[![Kubernetes](https://img.shields.io/badge/Kubernetes-Ready-326CE5.svg)](https://kubernetes.io/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

## 📋 Table of Contents

- [🏦 Trust Wave Bank](#-trust-wave-bank)
  - [📋 Table of Contents](#-table-of-contents)
  - [🚀 Overview](#-overview)
  - [🏗️ Architecture](#️-architecture)
  - [🔧 Microservices](#-microservices)
  - [💻 Technology Stack](#-technology-stack)
  - [🛠️ Prerequisites](#️-prerequisites)
  - [⚡ Quick Start](#-quick-start)
  - [🐳 Docker Setup](#-docker-setup)
  - [☸️ Kubernetes Deployment](#️-kubernetes-deployment)
  - [📊 Monitoring \& Observability](#-monitoring--observability)
  - [🔧 Configuration](#-configuration)
  - [📝 API Documentation](#-api-documentation)
  - [🧪 Testing](#-testing)
  - [🤝 Contributing](#-contributing)
  - [📄 License](#-license)
  - [👨‍💻 Author](#-author)

## 🚀 Overview

Trust Wave Bank is a comprehensive, enterprise-grade banking application designed with modern microservices architecture. It provides a complete suite of banking services including account management, card services, loan processing, and secure financial transactions.

### ✨ Key Features

- 🏦 **Account Management**: Complete CRUD operations for customer accounts
- 💳 **Card Services**: Credit/Debit card management and transactions
- 💰 **Loan Processing**: Loan application, approval, and management workflows
- 🔄 **Real-time Transactions**: Secure and fast financial transactions
- 📱 **Message Services**: Real-time notifications and communication
- 🔒 **Security**: Enterprise-level security with OAuth2 and JWT
- 📊 **Analytics**: Real-time monitoring and business intelligence
- 🌐 **Multi-platform**: RESTful APIs for web, mobile, and third-party integrations

## 🏗️ Architecture

Trust Wave Bank follows a **microservices architecture** pattern with the following design principles:

- **Domain Driven Design (DDD)**: Each service represents a distinct business domain
- **API-First Approach**: Well-defined REST APIs with OpenAPI documentation
- **Event-Driven Architecture**: Asynchronous communication using Apache Kafka
- **Circuit Breaker Pattern**: Resilience4J for fault tolerance
- **Distributed Configuration**: Centralized configuration management
- **Service Discovery**: Eureka for service registration and discovery

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web Client    │    │  Mobile App     │    │  Third Party    │
└─────────┬───────┘    └─────────┬───────┘    └─────────┬───────┘
          │                      │                      │
          └──────────────────────┼──────────────────────┘
                                 │
                    ┌────────────▼────────────┐
                    │     API Gateway         │
                    └────────────┬────────────┘
                                 │
         ┌───────────────────────┼───────────────────────┐
         │                       │                       │
┌────────▼────────┐    ┌────────▼────────┐    ┌────────▼────────┐
│  Accounts       │    │     Cards       │    │     Loans       │
│  Service        │    │    Service      │    │    Service      │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌────────────▼────────────┐
                    │   Message Service       │
                    └─────────────────────────┘
```

## 🔧 Microservices

| Service | Description | Port | Health Check |
|---------|-------------|------|--------------|
| **API Gateway** | Entry point for all client requests, routing, load balancing | 8072 | `/actuator/health` |
| **Config Server** | Centralized configuration management | 8071 | `/actuator/health` |
| **Eureka Discovery** | Service registration and discovery | 8070 | `/actuator/health` |
| **Accounts Service** | Customer account management and operations | 8080 | `/actuator/health` |
| **Cards Service** | Credit/Debit card lifecycle management | 9000 | `/actuator/health` |
| **Loans Service** | Loan application and processing | 8090 | `/actuator/health` |
| **Message Service** | Notifications and real-time messaging | 8082 | `/actuator/health` |

## 💻 Technology Stack

### Backend
- **Java 17+** - Latest LTS version with modern features
- **Spring Boot 3.x** - Application framework
- **Spring Cloud** - Microservices ecosystem
- **Spring Data JPA** - Data persistence layer
- **Spring Security** - Authentication and authorization
- **Apache Kafka** - Event streaming platform
- **Resilience4J** - Circuit breaker and retry mechanisms

### Data & Persistence
- **H2 Database** - In-memory database for development
- **JPA/Hibernate** - ORM framework
- **Flyway** - Database migration tool

### Service Discovery & Configuration
- **Netflix Eureka** - Service registry
- **Spring Cloud Config** - Configuration management
- **Spring Cloud Gateway** - API gateway

### Monitoring & Observability
- **Spring Boot Actuator** - Application monitoring
- **Micrometer + Prometheus** - Metrics collection
- **OpenTelemetry** - Distributed tracing
- **SpringDoc OpenAPI** - API documentation

### DevOps & Deployment
- **Docker** - Containerization
- **Kubernetes** - Container orchestration
- **Helm** - Kubernetes package manager
- **Jib** - Container image building
- **Maven** - Build automation

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit (JDK) 17 or higher**
  ```bash
  java --version
  ```
- **Apache Maven 3.8+**
  ```bash
  mvn --version
  ```
- **Docker & Docker Compose**
  ```bash
  docker --version
  docker-compose --version
  ```
- **Kubernetes cluster** (for K8s deployment)
- **Helm 3.x** (for Kubernetes deployment)

## ⚡ Quick Start

### 1. Clone the Repository
```bash
git clone https://github.com/harshal5-dev/trust-wave-bank.git
cd trust-wave-bank
```

### 2. Build All Services
```bash
# Build parent BOM first
mvn clean install -f trustwave-bom/pom.xml

# Build all microservices
mvn clean package -DskipTests
```

### 3. Start Infrastructure Services
```bash
# Start Config Server
cd config-server
mvn spring-boot:run &

# Start Eureka Discovery Server
cd ../eureka-discovery-server
mvn spring-boot:run &
```

### 4. Start Business Services
```bash
# Start in separate terminals or use & to run in background

# Accounts Service
cd accounts && mvn spring-boot:run &

# Cards Service
cd cards && mvn spring-boot:run &

# Loans Service
cd loans && mvn spring-boot:run &

# Message Service
cd message && mvn spring-boot:run &

# API Gateway (Start last)
cd api-gateway && mvn spring-boot:run &
```

### 5. Verify Services
- **Eureka Dashboard**: http://localhost:8070
- **API Gateway**: http://localhost:8072
- **Accounts Service**: http://localhost:8080/actuator/health
- **Cards Service**: http://localhost:9000/actuator/health
- **Loans Service**: http://localhost:8090/actuator/health

## 🐳 Docker Setup

### Using Docker Compose

1. **Navigate to docker-compose directory**:
```bash
cd docker-compose
```

2. **Start all services**:
```bash
docker-compose up -d
```

3. **View logs**:
```bash
docker-compose logs -f [service-name]
```

4. **Stop services**:
```bash
docker-compose down
```

### Build Individual Docker Images

```bash
# Build using Jib (configured in pom.xml)
mvn clean compile jib:dockerBuild

# Or build traditional Docker images
docker build -t trust-wave-bank/accounts ./accounts
docker build -t trust-wave-bank/cards ./cards
docker build -t trust-wave-bank/loans ./loans
```

## ☸️ Kubernetes Deployment

### Using Helm Charts

1. **Install Helm charts**:
```bash
cd helm
helm install trust-wave-bank ./trust-wave-bank
```

2. **Upgrade deployment**:
```bash
helm upgrade trust-wave-bank ./trust-wave-bank
```

3. **Check deployment status**:
```bash
kubectl get pods
kubectl get services
```

### Using Raw Kubernetes Manifests

```bash
cd kubernetes
kubectl apply -f .
```

### Access Services

```bash
# Port forward to access services locally
kubectl port-forward service/api-gateway 8072:8072
kubectl port-forward service/eureka-server 8070:8070
```

## 📊 Monitoring & Observability

### Health Checks
All services expose health endpoints:
```
GET /actuator/health
GET /actuator/info
GET /actuator/metrics
```

### Prometheus Metrics
Metrics are available at:
```
GET /actuator/prometheus
```

### Distributed Tracing
OpenTelemetry traces are configured for request tracking across services.

## 🔧 Configuration

### Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `CONFIG_SERVER_URL` | Config server URL | `http://localhost:8071` |
| `EUREKA_SERVER_URL` | Eureka server URL | `http://localhost:8070/eureka` |
| `KAFKA_BOOTSTRAP_SERVERS` | Kafka brokers | `localhost:9092` |
| `DATABASE_URL` | Database connection URL | `jdbc:h2:mem:testdb` |

### Application Profiles

- **default** - Development profile with H2 database
- **docker** - Docker environment configuration
- **kubernetes** - Kubernetes deployment configuration
- **prod** - Production environment settings

## 📝 API Documentation

### Swagger/OpenAPI Documentation

Once the services are running, access the API documentation:

- **Accounts API**: http://localhost:8080/swagger-ui.html
- **Cards API**: http://localhost:9000/swagger-ui.html
- **Loans API**: http://localhost:8090/swagger-ui.html

### Sample API Requests

#### Create Account
```bash
curl -X POST http://localhost:8072/api/accounts \
  -H "Content-Type: application/json" \
  -d '{
    "customerName": "John Doe",
    "email": "john.doe@example.com",
    "mobileNumber": "1234567890"
  }'
```

#### Get Account Details
```bash
curl -X GET http://localhost:8072/api/accounts/1234567890
```

## 🧪 Testing

### Unit Tests
```bash
mvn test
```

### Integration Tests
```bash
mvn verify -Pintegration-tests
```

### Contract Tests
```bash
mvn verify -Pcontract-tests
```

### Load Testing
```bash
# Using Apache Bench
ab -n 1000 -c 10 http://localhost:8072/api/accounts/health
```

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. **Fork the repository**
2. **Create a feature branch**:
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Commit your changes**:
   ```bash
   git commit -m 'Add some amazing feature'
   ```
4. **Push to the branch**:
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Open a Pull Request**

### Coding Standards

- Follow **Google Java Style Guide**
- Write comprehensive **unit tests**
- Update **documentation** for new features
- Ensure **CI/CD pipeline** passes

## 📄 License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Harshal** - [@harshal5-dev](https://github.com/harshal5-dev)

- 💼 [Portfolio](https://github.com/harshal5-dev/Portfolio)
- 🛒 [Cloud Kart Project](https://github.com/harshal5-dev/cloud-kart)
- ⚙️ [Configuration Management](https://github.com/harshal5-dev/cloud-kart-config)

---

<div align="center">
  <p>⭐ Star this repository if it helped you!</p>
  <p>🐛 Found a bug? <a href="https://github.com/harshal5-dev/trust-wave-bank/issues">Open an issue</a></p>
  <p>💡 Have a feature request? <a href="https://github.com/harshal5-dev/trust-wave-bank/issues">Let us know!</a></p>
</div>

---

**Built with ❤️ for the developer community**