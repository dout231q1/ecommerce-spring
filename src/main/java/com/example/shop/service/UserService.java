package com.example.shop.service;

import com.example.shop.database.entity.Cart;
import com.example.shop.database.entity.User;
import com.example.shop.database.repository.CartRepository;
import com.example.shop.database.repository.UserRepository;
import com.example.shop.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public UserService(UserRepository userRepository, CartRepository cartRepository){
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User createUser(User user){
        User savedUser = userRepository.save(user);
        cartRepository.save(new Cart(savedUser));
        return savedUser;
    }
}
