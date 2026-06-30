package com.example.shop.service;

import com.example.shop.database.entity.Cart;
import com.example.shop.database.entity.User;
import com.example.shop.database.repository.CartRepository;
import com.example.shop.database.repository.UserRepository;
import com.example.shop.exception.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnUser_whenIdExists() {
        User userFound = new User("Robert", 500.00);
        ReflectionTestUtils.setField(userFound, "id", 1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(userFound));

        User result = userService.findById(1L);

        assertNotNull(result);
        assertEquals("Robert", result.getUsername());
        assertEquals(500.00, result.getBalance());
    }

    @Test
    void shouldThrowException_whenIdDoesNotExist() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.findById(99L));
    }

    @Test
    void shouldReturnUserList_whenUsersExist() {
        User userOne = new User("Bruce", 200.00);
        User userTwo = new User("Robert", 500.00);
        List<User> userList = Arrays.asList(userOne, userTwo);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = userService.findAll();

        assertEquals(2, result.size());
        assertEquals("Bruce", result.get(0).getUsername());
        assertEquals("Robert", result.get(1).getUsername());
    }

    @Test
    void shouldReturnEmptyList_whenUsersDoesNotExist() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());

        List<User> result = userService.findAll();

        assertTrue(result.isEmpty());
        verify(userRepository).findAll();
    }

    @Test
    void shouldCreateUser_whenUserValid() {
        User createdUser = new User("Robert", 300.00);

        when(userRepository.save(any(User.class))).thenReturn(createdUser);

        User result = userService.save(createdUser);

        assertNotNull(result);
        assertEquals("Robert", result.getUsername());
        assertEquals(300.00, result.getBalance());
        verify(cartRepository).save(any(Cart.class));
    }
}