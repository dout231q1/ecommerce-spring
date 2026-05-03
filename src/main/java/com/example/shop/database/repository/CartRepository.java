package com.example.shop.database.repository;

import com.example.shop.database.entity.Cart;
import com.example.shop.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart getCartByUser(User user);
}
