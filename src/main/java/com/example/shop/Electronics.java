package com.example.shop;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn
public class Electronics extends Product {
    private Integer warrantyMonths;

    public Electronics(){}

    public Electronics(String name, Double price, Integer warrantyMonths) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
    }

    public Integer getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(Integer warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void updateFrom(Product other) {
        Electronics p = (Electronics) other;
        setWarrantyMonths(p.getWarrantyMonths());
    }
}
