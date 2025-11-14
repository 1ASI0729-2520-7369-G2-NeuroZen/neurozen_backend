# NeuroZen Platform API Documentation

## Overview

NeuroZen is a mental health and wellness platform built with Spring Boot 3.5.7, following Domain-Driven Design (DDD) principles.

## Technology Stack

- **Framework**: Spring Boot 3.5.7
- **Java Version**: 25
- **Database**: MySQL
- **Security**: Spring Security + JWT
- **Documentation**: Swagger/OpenAPI 3.0

## Architecture

The application follows DDD with bounded contexts:

- **IAM (Identity and Access Management)**: Authentication and user management
- **Appointment**: Medical appointment scheduling
- **Profile**: User profiles (patients and psychologists)
- **Assessment**: Stress assessments

## Authentication

### Base URL

- **Local**: `http://localhost:8080/api/v1`
- **Heroku**: `https://your-app-name.herokuapp.com/api/v1`

### Endpoints

#### 1. Sign Up (Register)

```http
POST /api/v1/auth/sign-up
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "password": "securePassword123",
  "firstName": "John",
  "lastName": "Doe",
  "roles": ["ROLE_USER"]
}
```

**Response (201 Created)**:

```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "roles": ["ROLE_USER"]
}
```

**Available Roles**:

- `ROLE_USER` - Regular user/patient
- `ROLE_PSYCHOLOGIST` - Mental health professional
- `ROLE_ADMIN` - Administrator

#### 2. Sign In (Login)

```http
POST /api/v1/auth/sign-in
Content-Type: application/json

{
  "username": "johndoe",
  "password": "securePassword123"
}
```

**Response (200 OK)**:

```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

### Using JWT Token

Include the token in the Authorization header for protected endpoints:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

## User Management

#### 3. Get All Users (Admin Only)

```http
GET /api/v1/users
Authorization: Bearer {token}
```

**Response (200 OK)**:

```json
[
  {
    "id": 1,
    "username": "johndoe",
    "email": "john@example.com",
    "firstName": "John",
    "lastName": "Doe",
    "roles": ["ROLE_USER"]
  }
]
```

#### 4. Get User by ID

```http
GET /api/v1/users/{userId}
Authorization: Bearer {token}
```

**Response (200 OK)**:

```json
{
  "id": 1,
  "username": "johndoe",
  "email": "john@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "roles": ["ROLE_USER"]
}
```

## Environment Variables

### Required for Production (Heroku)

```bash
# Database Configuration
DATABASE_URL=jdbc:mysql://host:port/database?useSSL=true&serverTimezone=UTC
DATABASE_USERNAME=your_db_username
DATABASE_PASSWORD=your_db_password

# JWT Configuration
JWT_SECRET=your-secret-key-at-least-256-bits-long
JWT_EXPIRATION_DAYS=7

# Server Configuration
PORT=8080
```

## Local Development Setup

### Prerequisites

- Java 25
- MySQL 8.0+
- Maven 3.8+

### Steps

1. **Clone the repository**

```bash
cd neurozen_backend
```

2. **Create MySQL database**

```sql
CREATE DATABASE neurozen_db;
```

3. **Configure application.properties**
   Update `src/main/resources/application.properties` with your local database credentials.

4. **Build the project**

```bash
./mvnw clean install
```

5. **Run the application**

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`

## Heroku Deployment

### 1. Create Heroku App

```bash
heroku create your-app-name
```

### 2. Add MySQL Database

```bash
heroku addons:create jawsdb:kitefin
```

### 3. Set Environment Variables

```bash
heroku config:set JWT_SECRET=your-secret-key-at-least-256-bits-long
heroku config:set JWT_EXPIRATION_DAYS=7
```

### 4. Deploy

```bash
git push heroku main
```

### 5. Verify Deployment

```bash
heroku logs --tail
heroku open
```

## CORS Configuration

The API is configured to accept requests from:

- `http://localhost:4200` (Angular development)
- `https://*.herokuapp.com` (Heroku deployments)

To add additional origins, update `WebSecurityConfiguration.java`:

```java
configuration.setAllowedOrigins(Arrays.asList(
  "http://localhost:4200",
  "https://*.herokuapp.com",
  "https://your-frontend-domain.com"
));
```

## API Documentation (Swagger)

### Interactive Documentation

Access the **Swagger UI** for interactive API testing:

- **Local**: `http://localhost:8080/swagger-ui.html`
- **Heroku**: `https://your-app-name.herokuapp.com/swagger-ui.html`

### OpenAPI Specification

Access the raw OpenAPI specification:

- **JSON**: `http://localhost:8080/api-docs`
- **YAML**: `http://localhost:8080/api-docs.yaml`

### Features

- ✅ Interactive API testing (no Postman needed!)
- ✅ JWT authentication support
- ✅ Request/response examples
- ✅ Model schemas
- ✅ Try out endpoints directly in browser

**See `SWAGGER_GUIDE.md` for detailed usage instructions.**

## Error Handling

### Common HTTP Status Codes

- `200 OK` - Request successful
- `201 Created` - Resource created successfully
- `400 Bad Request` - Invalid request data
- `401 Unauthorized` - Missing or invalid authentication
- `403 Forbidden` - Insufficient permissions
- `404 Not Found` - Resource not found
- `500 Internal Server Error` - Server error

### Error Response Format

```json
{
  "timestamp": "2025-01-15T10:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Username already exists",
  "path": "/api/v1/auth/sign-up"
}
```

## Security Best Practices

1. **Never commit sensitive data** (passwords, JWT secrets) to version control
2. **Use environment variables** for all sensitive configuration
3. **Use HTTPS** in production
4. **Rotate JWT secrets** regularly
5. **Implement rate limiting** for authentication endpoints
6. **Use strong passwords** (minimum 8 characters, mixed case, numbers, symbols)

## Testing

### Run Tests

```bash
./mvnw test
```

### Test Coverage

```bash
./mvnw verify
```

## Support

For issues or questions, please contact the development team or create an issue in the repository.
