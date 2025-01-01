package com.testify.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.testify.Service.UserService;
import com.testify.model.User;

class UserServiceTest {
    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testRegisterUser() {
        assertTrue(userService.registerUser("Jane", "Doe", "jane@example.com", "password", "student"));
    }

    @Test
    void testGetUserById() {
        User user = userService.getUserById(1);
        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    void testUpdateUser() {
        assertTrue(userService.updateUser(1, "Jane", "Smith", "jane@example.com", "newpassword", "student"));
    }

    @Test
    void testValidatePassword() {
        assertTrue(userService.validatePassword("test@example.com", "password"));
    }
}