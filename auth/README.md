# Auth Service

This is a Spring Boot-based authentication service that provides APIs for user registration and authentication. It uses JWT for secure token-based authentication and integrates with MySQL for data persistence.

## Features

- User registration and authentication
- JWT-based token generation
- Secure password storage using BCrypt
- OpenAPI documentation with Swagger UI
- CORS configuration for frontend integration
- MySQL database integration

## Prerequisites

- Java 21 or higher
- Maven 3.9.9 or higher
- MySQL database

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd auth
   ```

2. Configure environment variables:
   Create a `.env` file in the project root with the following variables:
   ```properties
   DB_URL=jdbc:mysql://<host>:<port>/<database>
   DB_USERNAME=<your-database-username>
   DB_PASSWORD=<your-database-password>
   JWT_SECRET=<your-jwt-secret>
   PORT=8080
   ```

3. Build the project:
   ```bash
   ./mvnw clean install
   ```

4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

5. Access the application:
   - API Base URL: `http://localhost:8080/api/auth`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

## API Endpoints

### Authentication

- **POST** `/api/auth/login`  
  Authenticate a user and return a JWT token.  
  **Request Body**:  
  ```json
  {
    "username": "user",
    "password": "password"
  }
  ```

- **POST** `/api/auth/register`  
  Register a new user.  
  **Request Body**:  
  ```json
  {
    "username": "user",
    "password": "password",
    "email": "user@example.com"
  }
  ```

## Database Schema

The application uses a `users` table with the following structure:
- `id` (Primary Key, Auto-Increment)
- `username` (Unique, Not Null)
- `password` (Not Null)
- `email` (Not Null)

## OpenAPI Documentation

The project uses SpringDoc to generate OpenAPI documentation. You can access the Swagger UI at `/swagger-ui.html` to explore and test the APIs.

## License

This project is licensed under the Apache License 2.0. See the `LICENSE` file for details.
