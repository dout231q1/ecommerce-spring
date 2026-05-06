# ecommerce-oop

A progressive e-commerce project — starting from a pure Java OOP implementation and evolving into a fully functional REST API with Spring Boot.

Each version introduces a new module, keeping the changes small and the evolution clear.

---

## What it covers

- REST API with Spring Boot
- JPA entities with inheritance (`JOINED` strategy) and relationships (`@OneToOne`, `@ManyToMany`, `@ManyToOne`)
- Service layer with business logic (balance validation, cart clearing on checkout)
- Strategy pattern for payment methods
- Error handling with custom exceptions

---

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Bean Validation (jakarta.validation)

---

## Endpoints

### Users
```http
GET  /user/{id}
POST /user
```

### Products
```http
GET    /products
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```

### Cart
```http
GET    /cart/{userId}
POST   /cart/{userId}/product/{productId}/add
DELETE /cart/{userId}/product/{productId}/remove
GET    /cart/{userId}/total
```

### Order
```http
POST /order/checkout/{userId}?payment=Pix
GET  /order/history/{userId}
```

#### Payment methods
```
Pix
Boleto
Credit Card  (5% fee applied)
```

---

## How to Run

```bash
# Clone the repository and switch to this branch
git clone https://github.com/dout231q1/ecommerce-oop
cd ecommerce-oop

# Configure your credentials in
src/main/resources/application.properties

# Run
./mvnw spring-boot:run
```

---

## Project Evolution

| Branch | Description |
|--------|-------------|
| [v1-oop](https://github.com/dout231q1/ecommerce-oop/tree/v1-oop) | Pure Java OOP |
| [v2-spring/user](https://github.com/dout231q1/ecommerce-oop/tree/v2-spring/user) | Spring Boot — User module |
| [v3-spring/product](https://github.com/dout231q1/ecommerce-oop/tree/v3-spring/product) | Spring Boot — Product module |
| [v4-spring/cart](https://github.com/dout231q1/ecommerce-oop/tree/v4-spring/cart) | Spring Boot — Cart module |
| [v5-spring/order](https://github.com/dout231q1/ecommerce-oop/tree/v5-spring/order) | Spring Boot — Order + Checkout |
