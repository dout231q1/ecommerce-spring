package com.example.shop.controller;


import com.example.shop.database.entity.Cart;
import com.example.shop.database.entity.Product;
import com.example.shop.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public Cart getCartByUserId(@PathVariable Long userId){
        return cartService.getCartByUser(userId);
    }

    @PostMapping("/{userId}/product/{productId}/add")
    public List<Product> addProductToCart(@PathVariable Long userId, @PathVariable Long productId){
        return cartService.addProductToCart(userId, productId);
    }

    @DeleteMapping("/{userId}/product/{productId}/remove")
    public Cart removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId){
        return cartService.removeProductFromCart(userId, productId);
    }

    @GetMapping("/{userId}/total")
    public Double getTotalPrice(@PathVariable Long userId){
        return cartService.getTotalPrice(userId);
    }
}
