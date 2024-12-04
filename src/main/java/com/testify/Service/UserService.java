package com.testify.Service;

import com.testify.dao.UserDAO;
import com.testify.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserService {

    private final UserDAO userDAO;
    private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

    // Constructor to initialize the UserDAO
    public UserService() {
        this.userDAO = new UserDAO();  // Initialize the DAO class
    }

    // Register a new user
    public boolean registerUser(String firstname, String lastname, String email, String password, String userRole) {
        // Create a new User object
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        // Hash password before storing it
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPasswrd(hashedPassword);

        user.setUserRole(userRole);
        user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

        try {
            // Check if user already exists by email
            if (userDAO.selectUserByEmail(email) != null) {
                return false; // User with this email already exists
            }

            // Insert the user into the database
            return userDAO.insertUser(user); // Insert the user into the database
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception occurred while registering user", e);
            return false;
        }
    }

    // Get user by ID
    public User getUserById(int id) {
        try {
            return userDAO.selectUser(id);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception occurred while retrieving user by ID", e);
            return null;
        }
    }

    // Get all users
    public List<User> getAllUsers() {
        try {
            return userDAO.selectAllUsers();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception occurred while retrieving all users", e);
            return null;
        }
    }

    // Update user details
    public boolean updateUser(int id, String firstname, String lastname, String email, String password, String userRole) {
        // Create a new User object with the updated details
        User user = new User();
        user.setId(id);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setEmail(email);

        // Hash password before storing it
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        user.setPasswrd(hashedPassword);

        user.setUserRole(userRole);
        user.setUpdatedAt(new java.sql.Timestamp(System.currentTimeMillis()));

        try {
            return userDAO.updateUser(user); // Update the user in the database
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception occurred while updating user", e);
            return false;
        }
    }

    // Delete user by ID
    public boolean deleteUser(int id) {
        try {
            return userDAO.deleteUser(id); // Delete the user from the database
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception occurred while deleting user", e);
            return false;
        }
    }

    // Check if a user exists by email
    public boolean userExistsByEmail(String email) {
        return userDAO.selectUserByEmail(email) != null; // Check if the user already exists
    }

    // Validate user's password during login
    public boolean validatePassword(String email, String password) {
        User user = userDAO.selectUserByEmail(email);
        if (user != null) {
            return BCrypt.checkpw(password, user.getPasswrd()); // Validate password using BCrypt
        }
        return false;
    }
}