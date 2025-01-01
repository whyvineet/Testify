package com.testify.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.testify.Service.AuthenticationService;
import com.testify.model.User;

class AuthenticationServiceTest {
    private AuthenticationService authService;

    @BeforeEach
    void setUp() {
        authService = new AuthenticationService();
    }

    @Test
    void testLoginSuccess() {
        assertTrue(authService.login("test@example.com", "password"));
    }

    @Test
    void testLoginFailure() {
        assertFalse(authService.login("test@example.com", "wrongpassword"));
    }

    @Test
    void testRegisterSuccess() {
        assertTrue(authService.register("John", "Doe", "john@example.com", "password", "student"));
    }

    @Test
    void testGetUserByEmail() {
        User user = authService.getUserByEmail("test@example.com");
        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
    }
}