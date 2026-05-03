package com.example.shop.service;

import com.example.shop.database.entity.Cart;
import com.example.shop.database.entity.Product;
import com.example.shop.database.entity.User;
import com.example.shop.database.repository.CartRepository;
import com.example.shop.database.repository.ProductRepository;
import com.example.shop.database.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart getCartByUser(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.getCartByUser(user);
        if(cart == null){
            cart = cartRepository.save(new Cart(user));
        }
        return cart;
    }

    public List<Product> addProductToCart(Long userId, Long productId){
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.getCartByUser(user);
        if(cart == null){
            cart = cartRepository.save(new Cart(user));
        }
        Product product = productRepository.findById(productId).orElseThrow();
        cart.getProducts().add(product);
        return cartRepository.save(cart).getProducts();
    }

    public Cart removeProductFromCart(Long userId, Long productId){
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.getCartByUser(user);
        if(cart == null){
            cart = cartRepository.save(new Cart(user));
        }
        Product product = productRepository.findById(productId).orElseThrow();
        cart.getProducts().remove(product);
        return cartRepository.save(cart);
    }

    public Double getTotalPrice(Long userId){
        User user = userRepository.findById(userId).orElseThrow();
        Cart cart = cartRepository.getCartByUser(user);
        if(cart == null){
            cart = cartRepository.save(new Cart(user));
        }
        double totalPrice = 0;
        for(Product product : cart.getProducts()){
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }
}
