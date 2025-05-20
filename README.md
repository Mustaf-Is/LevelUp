# LevelUp - Personal Growth and Habit Tracking Platform

![Java](https://img.shields.io/badge/Java-11%2B-blue.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![License](https://img.shields.io/github/license/Mustaf-Is/LevelUp)
![Build](https://img.shields.io/badge/build-passing-brightgreen.svg)
![Status](https://img.shields.io/badge/status-active-blue.svg)

LevelUp is a comprehensive web application designed to help users develop positive habits, track personal challenges, and foster continuous self-improvement. The platform enables users to create customized habits, join challenges, and monitor their progress over time.

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [API Documentation](#api-documentation)
  - [Authentication Endpoints](#authentication-endpoints)
  - [User Management](#user-management)
  - [Habit Management](#habit-management)
  - [Challenge Management](#challenge-management)
  - [Category Management](#category-management)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
- [Project Structure](#project-structure)
- [Authentication](#authentication)
- [Contributing](#contributing)
- [License](#license)

## 🚀 Features

- **User Management**: Register, login, and manage user profiles
- **Habit Tracking**: Create, update, and monitor personal habits
- **Challenge System**: Participate in time-bound challenges
- **Category Organization**: Organize habits and challenges by categories
- **Progress Monitoring**: Track completion and success rates
- **JWT Authentication**: Secure API endpoints with token-based authentication

## 🛠️ Technology Stack

- **Backend**: Java with Spring Boot
- **Security**: Spring Security with JWT
- **Database**: SQL database (configured via Spring Data JPA)
- **Frontend**: Web interface accessible via REST API endpoints
- **API**: RESTful architecture

## 📡 API Documentation

### 🔐 Authentication Endpoints

```http
POST /api/auth/register       # Register a new user
POST /api/auth/login          # Authenticate and receive JWT token
```

### 👤 User Management

```http
GET    /api/users             # Retrieve all users
GET    /api/users/{id}        # Get user by ID
POST   /api/users             # Create a new user
PUT    /api/users/{id}        # Update user information
DELETE /api/users/{id}        # Delete a user
```

### 📊 Habit Management

```http
GET    /api/habits            # Retrieve all habits
GET    /api/habits/{id}       # Get habit by ID
POST   /api/habits            # Create a new habit
PUT    /api/habits/{id}       # Update habit information
DELETE /api/habits/{id}       # Delete a habit
```

### 🏆 Challenge Management

```http
GET    /api/challenges        # Retrieve all challenges
GET    /api/challenges/{id}   # Get challenge by ID
POST   /api/challenges        # Create a new challenge
PUT    /api/challenges/{id}   # Update challenge information
DELETE /api/challenges/{id}   # Delete a challenge
```

### 🗂️ Category Management

```http
GET    /api/categories        # Retrieve all categories
GET    /api/categories/{id}   # Get category by ID
POST   /api/categories        # Create a new category
PUT    /api/categories/{id}   # Update category information
DELETE /api/categories/{id}   # Delete a category
```

## ⚙️ Getting Started

### Prerequisites

- Java JDK 11 or higher
- Maven
- MySQL or compatible database

### Installation

1. Clone the repository

```bash
git clone https://github.com/Mustaf-Is/LevelUp.git
```

2. Navigate to the project directory

```bash
cd LevelUp
```

3. Configure database connection
   - Update `application.properties` with your database credentials.

4. Build the project

```bash
mvn clean install
```

5. Run the application

```bash
mvn spring-boot:run
```

6. Access the application
   - URL: `http://localhost:8080`

## 🧱 Project Structure

```
LevelUp/
├── controllers/       # Handle HTTP requests and responses
├── services/          # Implement business logic
├── repositories/      # Manage data access
├── models/            # Define data entities
├── dtos/              # Transfer data between layers
├── mappers/           # Convert between entities and DTOs
└── security/          # Configure authentication and authorization
```

## 🔒 Authentication

LevelUp uses JWT (JSON Web Token) for authentication.

- Users register or login via `/api/auth`
- On successful login, a JWT token is returned
- This token must be included in the `Authorization` header (`Bearer <token>`) for protected endpoints
- Tokens expire after 24 hours

### 🔁 Example Authentication Flow

**Request**

```json
POST /api/auth/login
{
  "email": "user@example.com",
  "password": "securepassword"
}
```

**Response**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

## 🤝 Contributing

Contributions are welcome! To contribute:

1. Fork the repository
2. Create a branch

```bash
git checkout -b feature/amazing-feature
```

3. Commit your changes

```bash
git commit -m "Add some amazing feature"
```

4. Push to the branch

```bash
git push origin feature/amazing-feature
```

5. Open a Pull Request

## 📄 License

This project is licensed under the XXX License.

© 2025 LevelUp. All rights reserved.
