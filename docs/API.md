# API Documentation

## Authentication Endpoints

### POST /auth
Login endpoint for user authentication.

**Request Body:**
```json
{
  "email": "string",
  "password": "string"
}
```

**Response:**
- 200: Successful login
- 401: Invalid credentials
- 500: Server error

### POST /signup
Register a new user.

**Request Body:**
```json
{
  "firstname": "string",
  "lastname": "string",
  "email": "string",
  "password": "string",
  "userRole": "string"
}
```

**Response:**
- 201: User created
- 400: Invalid input
- 409: Email already exists

## Exam Endpoints

### POST /createExam
Create a new exam.

**Request Body:**
```json
{
  "title": "string",
  "description": "string"
}
```

**Response:**
- 201: Exam created
- 400: Invalid input
- 401: Unauthorized

### GET /dashboard
Get user dashboard data.

**Response:**
- 200: Success
- 401: Unauthorized