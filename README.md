# ecommerce-oop

> Evolution of the [v1-oop](https://github.com/dout231q1/ecommerce-oop/tree/v1-oop) project. Rewriting the same e-commerce system from scratch using Spring Boot, JPA and PostgreSQL.

## v5 — Order & Checkout

Everything from previous versions, now with an order and checkout system.

Checkout pulls the products directly from the user's cart, validates if the balance is enough, processes the payment and saves the order — all in a single transaction. If anything fails, nothing is committed.

The payment system uses a strategy pattern. `PaymentMethod` is an interface, and each method implements its own logic. Pix and Boleto charge the exact total, Credit Card adds a 5% fee. The service just calls `pay()` and lets each implementation handle the math.

After a successful checkout, the cart is cleared automatically.

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Bean Validation (jakarta.validation)

## Endpoints

### Order
```http
POST /order/checkout/{userId}?payment=Pix
GET  /order/history/{userId}
```

### Payment methods
```
Pix
Boleto
Credit Card  (5% fee applied)
```

### Cart
```http
GET    /cart/{userId}
POST   /cart/{userId}/product/{productId}/add
DELETE /cart/{userId}/product/{productId}/remove
GET    /cart/{userId}/total
```

### Products
```http
GET    /products
POST   /products
PUT    /products/{id}
DELETE /products/{id}
```

### User
```http
GET  /user/{id}
POST /user
```

## How to Run
```bash
# Clone the repository and switch to this branch
git clone https://github.com/dout231q1/ecommerce-oop
cd ecommerce-oop
git checkout v5-spring/order

# Configure your credentials in
src/main/resources/application.properties

# Run
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
| [v4-spring/cart](https://github.com/dout231q1/ecommerce-oop/tree/v4-spring/cart) | Spring Boot — Cart module |
| [v5-spring/order](https://github.com/dout231q1/ecommerce-oop/tree/v5-spring/order) | Spring Boot — Order + Checkout |
