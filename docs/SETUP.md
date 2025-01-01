# Setup Guide

## Prerequisites
- Java 11 or higher
- MySQL 8.0
- Maven 3.6+

## Database Setup
1. Create MySQL database:
```sql
CREATE DATABASE testifydb;
```

2. Configure database connection in `DatabaseConnection.java`:
```java
private static final String URL = "jdbc:mysql://localhost:3306/testifydb";
private static final String USER = "your_username";
private static final String PASSWORD = "your_password";
```

## Building the Project
1. Clone the repository
2. Run `mvn clean install`
3. Deploy the WAR file to your servlet container

## Configuration
1. Set up logging configuration
2. Configure security settings
3. Set up mail server (if needed)