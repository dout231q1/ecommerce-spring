# ecommerce-oop

> Evolution of the [v1-oop](https://github.com/dout231q1/ecommerce-oop/tree/v1-oop) project.
> Rewriting the same e-commerce system from scratch using Spring Boot, JPA and PostgreSQL.

## v3 — Products

Everything from v2, now with a full product system. Products have three types —
Electronics, Food and Clothing — each with their own fields and stored in separate
tables using JPA's JOINED inheritance strategy.

`Product` is abstract, so you always have to specify a type. No generic products allowed.
Each subclass implements `updateFrom()`, which keeps the update logic where it belongs —
inside the class itself, not scattered across the service.

## Endpoints

### Products
```http
GET    /products
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```

### Users (from v2)
```http
GET  /user/{id}
POST /user
```

## Product types

```json
{ "type": "electronics", "name": "TV", "price": 500.0, "warrantyMonths": 12 }
{ "type": "food", "name": "Rice", "price": 6.0, "expirationDate": "2026-12-31" }
{ "type": "clothing", "name": "T-shirt", "price": 50.0, "clothingSize": "M" }
```

## How to Run

```bash
git clone https://github.com/dout231q1/ecommerce-oop
cd ecommerce-oop
git checkout v3-spring/product
# create the database, configure application.properties, then:
./mvnw spring-boot:run
```

## Requirements
- JDK 17+
- PostgreSQL

## Project Evolution

| Branch | Description |
|--------|-------------|
| [v1-oop](https://github.com/dout231q1/ecommerce-oop/tree/v1-oop) | Pure Java OOP |
| [v2-spring/user](https://github.com/dout231q1/ecommerce-oop/tree/v2-spring/user) | Spring Boot — User module |
| [v3-spring/product](https://github.com/dout231q1/ecommerce-oop/tree/v3-spring/product) | Spring Boot — Product module |
| v4-spring/cart | Spring Boot — Cart module |
| v5-spring/order | Spring Boot — Order + Checkout |
