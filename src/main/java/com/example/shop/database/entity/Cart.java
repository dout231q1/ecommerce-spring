package com.example.shop.database.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @ManyToMany
    private List<Product> products = new ArrayList<>();

    public Cart(){}

    public Cart(User user){
        this.user = user;
        this.products = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public User getUser(){
        return user;
    }

    public List<Product> getProducts(){
        return products;
    }
}
