package com.example.shop;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn
public class Clothing extends Product {
    private String clothingSize;

    public Clothing(){}

    public Clothing(String name, Double price, String clothingSize) {
        super(name, price);
        this.clothingSize = clothingSize;
    }

    public String getClothingSize() {
        return clothingSize;
    }

    public void setClothingSize(String clothingSize) {
        this.clothingSize = clothingSize;
    }

    @Override
    public void updateFrom(Product other) {
        Clothing p = (Clothing) other;
        setClothingSize(p.getClothingSize());
    }
}
