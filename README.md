# ecommerce-oop

A simulated e-commerce store system built with **Java**, using only Object-Oriented Programming concepts. This project was built as a portfolio piece to practice and demonstrate core OOP principles without frameworks, databases, or graphical interfaces.

## OOP Concepts Applied

- **Inheritance** — `Electronics`, `Clothing` and `Food` extend `Product`
- **Polymorphism** — `PixPayment`, `CreditCardPayment` and `BoletoPayment` implement `PaymentMethod`
- **Encapsulation** — all attributes are `private` with controlled access via getters
- **Composition** — `User` has a `Cart`, `Cart` has a list of `Product`
- **Interface** — `PaymentMethod` defines the contract for all payment types
- **Lambda** — used in `Cart.removeProduct()` with `removeIf`

## Features

- Product catalog with multiple product types
- Add products to cart by name
- Remove products from cart by name
- Display cart contents and total price
- Balance validation before checkout
- 3 payment methods with different behaviors:
  - Pix — instant payment, no extra charge
  - Boleto — instant payment, no extra charge
  - Credit Card — applies 5% tax on the total
- Balance is correctly discounted after purchase, including credit card tax
- Separate `CartTest` class to test cart operations in isolation

## Project Structure

```
src/
├── Main.java
├── CartTest.java
├── Product.java
├── Electronics.java
├── Clothing.java
├── Food.java
├── Cart.java
├── User.java
├── PaymentMethod.java
├── PixPayment.java
├── BoletoPayment.java
└── CreditCardPayment.java
```
## Requirements

- [JDK 11+](https://www.oracle.com/java/technologies/downloads/)

## How to Run

```bash
# Clone the repository
git clone https://github.com/dout231q1/ecommerce-oop

# Navigate to the project folder
cd ecommerce-oop

# Compile
javac src/*.java -d out

# Run
java -cp out Main
```

## Example Output

```
=== Products Display ===
Name: Keyboard, Price: $80.0
Name: Mouse, Price: $60.0
Name: TV, Price: $200.0, Warranty (In months): 24
Name: PC, Price: $2500.0, Warranty (In months): 12
Name: T-Shirt, Price: $25.0, Clothing Size: G
Name: Jeans, Price: $55.0, Clothing Size: M
Name: Frozen Pizza, Price: $7.0, Expiration Date: 2026-05-10
Name: Doritos, Price: $12.0, Expiration Date: 2026-06-15

=== Cart ===
Name: Keyboard, Price: $80.0
Name: Mouse, Price: $60.0
Name: T-Shirt, Price: $25.0
Total price: $165.0

=== Payment ===
Balance: $500.0
Calculating credit card tax...
Tax amount: $8.25 plus product's total: $165.0
Done!
$173.25 payment made via Credit Card.
Remaining balance: $326.75
```

## Future Improvements

- Add product quantity support
- Implement discount coupons
- Add shipping calculation
- Reimplement as a REST API using Spring Boot
