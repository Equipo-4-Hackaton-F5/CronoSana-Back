# 💊 CronoSana - Medicine Schedule Management System

A comprehensive REST API for medicine schedule management designed to help users organize and track their medication intake routines effectively.

![Java](https://img.shields.io/badge/Java%2021-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot%203.5.5-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-BC4521?style=for-the-badge&logo=lombok&logoColor=white)![Spring Boot DevTools](https://img.shields.io/badge/Spring%20DevTools-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Jakarta Validation](https://img.shields.io/badge/Jakarta%20Validation-ED8B00?style=for-the-badge&logo=jakarta&logoColor=white)
![JUnit 5](https://img.shields.io/badge/JUnit%205-25A162?style=for-the-badge&logo=junit5&logoColor=white)

## 📋 Table of Contents

- [🎯 Project Overview](#-project-overview)
- [✨ Features](#-features)
- [🚀 Getting Started](#-getting-started)
- [⚙️ Installation](#-installation)
- [🏃‍♂️ Running the Application](#-running-the-application)
- [📚 API Documentation](#-api-documentation)
- [🌐 API Endpoints](#-api-endpoints)
- [📊 Architecture](#-architecture)
- [🧪 Tests](#-tests)
- [🤝 Contributing](#-contributing)

## 🎯 Project Overview

**CronoSana** addresses the critical challenge of medication management in healthcare. Many patients struggle with **complex medication schedules**, missed doses, and lack of proper tracking systems. Traditional methods like paper notes, phone alarms, or generic reminder apps often fail to provide comprehensive medication management.

This gap leads to medication non-adherence, health complications, and inefficient healthcare outcomes. **CronoSana emerges as the comprehensive solution** to digitalize and optimize medication schedule management.

### 🎯 Specific Objectives

- **Centralized Medicine Management**: Store and organize all medication information
- **Smart Scheduling**: Create flexible dosing schedules based on user needs
- **Dose Tracking**: Monitor medication intake with detailed logging
- **Status Management**: Track PENDING, TAKEN, and MISSED doses

## ✨ Features

- 💊 **Medicine Management**: Complete CRUD operations for medication registry
- 📅 **Schedule Creation**: Flexible scheduling with customizable timing intervals
- 📊 **Dose Logging**: Tracking of medication intake with timestamps
- 🚨 **Status Tracking**: PENDING → TAKEN → MISSED dose status management

## 🚀 Getting Started

### Prerequisites

Before you begin, ensure you have the following installed:

- **Java 21+** - [Download here](https://www.oracle.com/java/technologies/downloads/)
- **Maven 3.6+** - [Download here](https://maven.apache.org/download.cgi)
- **MySQL 8.0+** - [Download here](https://dev.mysql.com/downloads/)
- **Git** - [Download here](https://git-scm.com/)

### Quick Start

```bash
# Clone the repository
git clone https://github.com/Equipo-4-Hackaton-F5/CronoSana-Back.git
cd CronoSana-Back

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

### Frontend Integration

For the complete user interface, check out our React frontend:
**[CronoSana Frontend Repository](https://github.com/Equipo-4-Hackaton-F5/CronoSana-Front)**

## ⚙️ Installation

### 1. Clone the Repository
```bash
git clone https://github.com/Equipo-4-Hackaton-F5/CronoSana-Back.git
cd CronoSana-Back
```

### 2. Configure Database
Create a MySQL database and configure your environment variables in `.env` file:

```env
# Database Configuration
DB_URL=jdbc:mysql://localhost:3306/cronosana_db
DB_USERNAME=your_username
DB_PASSWORD=your_password

# Server Configuration
SERVER_PORT=8080
```

### 3. Configure Application
The application uses `application.yml` for configuration:

```yml
spring:
  application:
    name: CronoSana
  
  datasource:
    url: ${DB_URL}
    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}
  
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true
    properties:
      hibernate:
        format_sql: true

server:
  port: ${SERVER_PORT:8080}
```

### 4. Build the Project
```bash
./mvnw clean install
```

## 🏃‍♂️ Running the Application

### Development Mode
```bash
./mvnw spring-boot:run
```

### Production Mode
```bash
# Build JAR
./mvnw clean package

# Run JAR
java -jar target/CronoSana-0.0.1-SNAPSHOT.jar
```

The API will be available at: `http://localhost:8080`

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api/
```

### Response Format

## 🌐 API Endpoints

### 💊 Medicine Management
| Method | Endpoint | Description | Body Required |
|--------|----------|-------------|---------------|
| **GET** | `/api/medicines` | List all medicines | No |
| **GET** | `/api/medicines/{id}` | Get medicine by ID | No |
| **POST** | `/api/medicines` | Create new medicine | Yes |
| **PUT** | `/api/medicines/{id}` | Update medicine | Yes |
| **DELETE** | `/api/medicines/{id}` | Delete medicine | No |

### 📅 Schedule Management
| Method | Endpoint | Description | Body Required |
|--------|----------|-------------|---------------|
| **GET** | `/api/schedules` | List all schedules | No |
| **GET** | `/api/schedules/{id}` | Get schedule by ID | No |
| **POST** | `/api/schedules` | Create new schedule | Yes |
| **PUT** | `/api/schedules/{id}` | Update schedule | Yes |
| **DELETE** | `/api/schedules/{id}` | Delete schedule | No |

### 📊 Dose Log
| Method | Endpoint | Description | Body Required |
|--------|----------|-------------|---------------|
| **GET** | `/api/dose-logs` | List all dose logs | No |
| **GET** | `/api/dose-logs/{id}` | Get dose log by ID | No |
| **POST** | `/api/dose-logs` | Create new dose log | Yes |
| **PUT** | `/api/dose-logs/{id}` | Update dose log | Yes |
| **DELETE** | `/api/dose-logs/{id}` | Delete dose log | No |

### Status Values
- `PENDING` - Dose is scheduled but not taken
- `TAKEN` - Dose has been consumed
- `MISSED` - Dose was skipped or forgotten

## 📊 Architecture

### Database Design
The application uses the following main entities:

#### Medicine Entity
- **id**: Primary key
- **name**: Medicine name
- **dose**: Dosage information

#### Schedule Entity
- **id**: Primary key
- **firstTake**: Initial dose timestamp
- **timesPerDay**: Number of doses per day
- **medicineId**: Foreign key to Medicine

#### DoseLog Entity
- **id**: Primary key
- **scheduleId**: Foreign key to Schedule
- **scheduledHour**: When dose should be taken
- **takenAt**: When dose was actually taken
- **status**: Current dose status (PENDING/TAKEN/MISSED)

### Entity Relationships
```
Medicine (1) ←→ (1) Schedule (1) ←→ (*) DoseLog
```

### Layer Architecture
```
Controller Layer
    ↓
Service Layer (Business Logic)
    ↓
Repository Layer (Data Access)
    ↓
Database Layer (MySQL)
```

## 🧪 Tests

The application includes comprehensive testing:

- **Unit Tests**: Service layer business logic validation
- **Integration Tests**: Repository and database interaction testing
- **Controller Tests**: API endpoint validation

### Running Tests
```bash
# Run all tests
./mvnw test

# Run tests with detailed output
./mvnw test -X

# Run specific test class
./mvnw test -Dtest=MedicineServiceTest
```

### Test Coverage
Tests cover:
- CRUD operations for all entities
- Validation logic
- Error handling
- Database constraints
- Mapper functionality

## 🤝 Contributing

Contributions are welcome! Please follow these guidelines:

### Development Workflow
1. **Fork the repository**
2. **Create a feature branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Follow conventional commits**
   ```bash
   git commit -m "feat: add medicine reminder functionality"
   git commit -m "fix: resolve schedule overlap issue"
   git commit -m "docs: update API documentation"
   ```
4. **Push to the branch**
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Open a Pull Request**

### Code Standards
- Follow Java naming conventions
- Use DTOs for API requests/responses
- Implement proper exception handling
- Write comprehensive tests
- Use meaningful commit messages
- Add Javadoc for public methods
- Follow REST API best practices

### Commit Convention
- `feat:` new features
- `fix:` bug fixes
- `docs:` documentation changes
- `style:` formatting changes
- `refactor:` code refactoring
- `test:` adding or updating tests
- `chore:` maintenance tasks

## 🔮 Future Enhancements

- 🔔 **Push Notifications**: Mobile and web push notifications for dose reminders
- 👥 **User Management**: Multi-user support with authentication
- 📊 **Advanced Reporting**: Detailed medication history and compliance reports

## Authors

<table align="center">
  <tr>
    <td align="center">
      <a href="https://github.com/Equipo-4-Hackaton-F5">
        <img src="https://github.com/Equipo-4-Hackaton-F5.png" width="100px;" alt="Equipo 4"/>
        <br />
        <sub><b>Equipo 4 Hackathon F5</b></sub>
      </a>
      <br />
      <sub>Full Stack Development Team</sub>
    </td>
  </tr>
</table>

---

⚡ **Built with passion for healthcare technology** ⚡