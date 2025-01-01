package com.testify.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.testify.model.User;
import java.util.List;

class UserDAOTest {
    private UserDAO userDAO;

    @BeforeEach
    void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    void testInsertUser() throws Exception {
        User user = new User();
        user.setFirstname("Test");
        user.setLastname("User");
        user.setEmail("test@example.com");
        user.setPasswrd("password");
        user.setUserRole("student");
        assertTrue(userDAO.insertUser(user));
    }

    @Test
    void testSelectUserByEmail() {
        User user = userDAO.selectUserByEmail("test@example.com");
        assertNotNull(user);
        assertEquals("test@example.com", user.getEmail());
    }

    @Test
    void testUpdateUser() throws Exception {
        User user = new User();
        user.setId(1);
        user.setFirstname("Updated");
        user.setLastname("User");
        assertTrue(userDAO.updateUser(user));
    }
}