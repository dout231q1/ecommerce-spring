# ecommerce-oop

> Evolution of the [v1-oop](https://github.com/dout231q1/ecommerce-oop/tree/v1-oop) project. Rewriting the same e-commerce system from scratch using Spring Boot, JPA and PostgreSQL instead of pure OOP.

## v2 — User

This version implements only the User module as a REST API with database persistence.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL

## Endpoints

### Create user
```http
POST /user
Content-Type: application/json

{
  "username": "Betina",
  "balance": 1042000.0
}
```

### Get user by ID
```http
GET /user/{id}
```

### Get all users
```http
GET /user
```

## Requirements

- JDK 17+
- PostgreSQL

## How to Run

First, create the database with any client of your choice:
- CREATE DATABASE ecommerce;

```bash
# Clone the repository and switch to this branch
git clone https://github.com/dout231q1/ecommerce-oop
cd ecommerce-oop
git checkout v2-spring/user

# Configure your credentials in
src/main/resources/application.properties

# Run
./mvnw spring-boot:run
```

## Project Evolution

| Branch | Description |
|--------|-------------|
| [v1-oop](https://github.com/dout231q1/ecommerce-oop/tree/v1-oop) | Java OOP |
| [v2-spring/user](https://github.com/dout231q1/ecommerce-oop/tree/v2-spring/user) | Spring Boot — User module |
