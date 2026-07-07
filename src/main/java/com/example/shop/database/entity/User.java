package com.example.shop.database.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the user", example = "1")
    private Long id;

    @NotBlank(message = "Username is required.")
    @Schema(description = "Name chosen by the user to identify their account", example = "Robert Pattinson")
    private String username;

    @NotNull(message = "Balance is required.")
    @PositiveOrZero(message = "Balance cannot be negative.")
    @Schema(description = "Current amount of money available in the user's account", example = "1500.00")
    private Double balance;

    public User(){}

    public User(String username, Double balance){
        this.username = username;
        this.balance = balance;
    }

    public Long getId(){
        return id;
    }

    public String getUsername(){
        return username;
    }

    public Double getBalance(){
        return balance;
    }

    public void setBalance(Double balance){
        this.balance = balance;
    }
}
