# Payment System

![Java](https://img.shields.io/badge/Java-23-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-blue)
![Maven](https://img.shields.io/badge/Maven-3.9-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

A fintech payment and wallet management system built with Java and Spring Boot.

---

## 📖 Overview

**Payment System** is a payment and wallet management system built with **Java** and **Spring Boot**. It simulates real-world fintech systems like **SnappPay** and **DigiPay**, including user management, wallets, transactions, and orders.

The goal of this project is to learn and implement **professional architecture** and **design patterns** in a real-world project.

---

## 🎯 Project Goals

- 🎓 **Learn professional architecture** — proper layering, separation of concerns
- 🔐 **Implement security** — JWT, authentication and authorization
- ⚡ **Optimize performance** — Redis, multi-threading
- 🔄 **Real-time communication** — WebSocket, RabbitMQ
- 🧩 **Follow SOLID principles** and design patterns
- 💼 **Build a portfolio project** — complete and presentable

---

## ✨ Features

### 🚧 Phase 1: Core (In Progress)
- [x] User management
- [x] Wallet management
- [x] Transaction management
- [x] Order management
- [x] Full CRUD for all entities
- [x] DTO Pattern (Request/Response)
- [x] Mapper Pattern
- [x] Validation (`@Valid`, `@NotBlank`, `@Email`, `@Pattern`)
- [x] Global Exception Handling
- [x] Custom ErrorResponse
- [ ] API testing with Postman

### 📋 Phase 2: Security
- [ ] Spring Security
- [ ] JWT Authentication
- [ ] Password encryption
- [ ] Role-based access control (RBAC)

### 📋 Phase 3: Advanced Business Logic
- [ ] Design Patterns (Strategy, Factory)
- [ ] Fee calculation
- [ ] Credit management

### 📋 Phase 4: Concurrency
- [ ] Multi-threading
- [ ] Concurrent transaction processing

### 📋 Phase 5: Cache & Real-time
- [ ] Redis Cache
- [ ] WebSocket Notifications

### 📋 Phase 6: Scalability
- [ ] RabbitMQ
- [ ] Clean Architecture
- [ ] Event-Driven Design

---

## 🛠️ Tech Stack

### Backend
- **Java 23**
- **Spring Boot 4.1.1**
- **Spring Data JPA**
- **Spring Security** (Phase 2)
- **Spring WebSocket** (Phase 5)

### Database
- **PostgreSQL 18**
- **Redis** (Phase 5)
- **RabbitMQ** (Phase 6)

### Tools & Libraries
- **Lombok**
- **Maven**
- **Docker & Docker Compose**
- **JUnit 5**
- **Postman**

---

## 🏗️ Architecture

The project uses a **Layered Architecture**:

<pre>
┌─────────────────────────────────────┐
│         Controller Layer            │  ← REST API
├─────────────────────────────────────┤
│          Service Layer              │  ← Business Logic
├─────────────────────────────────────┤
│        Repository Layer             │  ← Database Access
├─────────────────────────────────────┤
│         Entity Layer                │  ← Entities
└─────────────────────────────────────┘
</pre>

**Principles followed:**
- ✅ SOLID Principles
- ✅ Separation of Concerns
- ✅ Dependency Injection
- ✅ DTO Pattern
- ✅ Repository Pattern

---

## 📂 Project Structure

<pre>
payment-system/
├── src/
│   ├── main/
│   │   ├── java/com/fintech/paymentsystem/
│   │   │   ├── config/           # Configuration (Security, Redis, ...)
│   │   │   ├── controller/       # REST API endpoints
│   │   │   ├── dto/              # Data Transfer Objects
│   │   │   │   ├── request/
│   │   │   │   └── response/
│   │   │   ├── entity/           # Database entities
│   │   │   │   └── enums/        # Enums
│   │   │   ├── exception/        # Exception handling
│   │   │   ├── mapper/           # Entity ↔ DTO mapping
│   │   │   ├── repository/       # Database access
│   │   │   ├── security/         # JWT & Security (Phase 2)
│   │   │   ├── service/          # Business logic
│   │   │   ├── util/             # Utilities
│   │   │   └── PaymentSystemApplication.java
│   │   └── resources/
│   │       └── application.yml   # Configuration
│   └── test/                     # Tests
├── docker-compose.yml
├── Dockerfile
├── pom.xml
└── README.md
</pre>

---

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.8 or higher
- PostgreSQL 15 or higher
- Docker (optional)

### 1. Clone the repository

<pre>
git clone https://github.com/alireza-davatgar/payment-system.git
cd payment-system
</pre>

### 2. Create the database

<pre>
CREATE DATABASE fintech_db;
</pre>

### 3. Configure `application.yml`

<pre>
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/fintech_db
    username: postgres
    password: your_password
</pre>

### 4. Run the application

<pre>
mvn spring-boot:run
</pre>

Or run `PaymentSystemApplication` in IntelliJ IDEA.

### 5. Access the API

<pre>
http://localhost:8080/api/users
</pre>

---

## 📡 API Endpoints

### User APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users` | Create a new user |
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| GET | `/api/users/username/{username}` | Get user by username |

### Wallet APIs

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/wallets/{id}` | Get wallet by ID |
| GET | `/api/wallets/user/{userId}` | Get wallet by user ID |
| GET | `/api/wallets/number/{walletNumber}` | Get wallet by wallet number |
| POST | `/api/wallets/{id}/deposit` | Deposit money |
| POST | `/api/wallets/{id}/withdraw` | Withdraw money |

### Sample Request

**Create User:**

<pre>
POST /api/users
Content-Type: application/json

{
    "username": "ali_reza",
    "email": "ali@example.com",
    "password": "123456",
    "fullName": "Ali Rezaei",
    "phoneNumber": "09123456789"
}
</pre>

**Response:**

<pre>
{
    "id": 1,
    "username": "ali_reza",
    "email": "ali@example.com",
    "fullName": "Ali Rezaei",
    "phoneNumber": "09123456789",
    "role": "USER",
    "enabled": true,
    "createdAt": "2026-09-15T11:10:00",
    "updatedAt": "2026-09-15T11:10:00"
}
</pre>


**Validation Error Response (400 Bad Request):**

<pre>
{
"timestamp": "2026-09-20T11:10:00",
"status": 400,
"error": "Validation Failed",
"message": "One or more fields are invalid",
"path": "/api/users",
"validationErrors": {
"username": "Username must be between 3 and 50 characters",
"email": "Email should be valid",
"password": "Password must be at least 6 characters"
}
}
</pre>
---

## 🗺️ Roadmap

- [ ] Phase 1: Core (In Progress)
    - [x] Entities, Repositories, Services
    - [x] DTO Pattern
    - [x] Mapper Pattern
    - [x] Validation
    - [x] Global Exception Handling
    - [x] Wallet APIs (Deposit, Withdraw)
    - [x] API testing with Postman (User & Wallet)
    - [ ] Transaction APIs
    - [ ] Order APIs
    - [ ] API Testing with Postman
- [ ] Phase 2: Security & JWT
- [ ] Phase 3: Advanced Business Logic
- [ ] Phase 4: Multi-threading
- [ ] Phase 5: Redis & WebSocket
- [ ] Phase 6: RabbitMQ & Clean Architecture
- [ ] Phase 7: Full Testing
- [ ] Phase 8: Docker & CI/CD

---

## 🤝 Contributing

Contributions are welcome! Here's how:

1. Fork the project
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

## 📞 Contact

- **GitHub:** [@alireza-davatgar](https://github.com/alireza-davatgar)
- **LinkedIn:** [Alireza Davatgar](https://www.linkedin.com/in/alireza-davatgar/)
- **Email:** alireza.davat@gmail.com

---

⭐ If you find this project useful, please give it a star!