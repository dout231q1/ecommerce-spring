package com.example.shop;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn
public class Food extends Product {
    private LocalDate expirationDate;

    public Food(){}

    public Food(String name, Double price, LocalDate expirationDate) {
        super(name, price);
        this.expirationDate = expirationDate;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public void updateFrom(Product other) {
        Food p = (Food) other;
        setExpirationDate(p.getExpirationDate());
    }
}
