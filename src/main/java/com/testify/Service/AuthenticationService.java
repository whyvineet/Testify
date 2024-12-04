package com.testify.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.testify.model.User;
import com.testify.util.DatabaseConnection;

public class AuthenticationService {

    // User login method
    public boolean login(String email, String password) {
        String query = "SELECT passwrd FROM users WHERE email = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("passwrd");
                    return storedPassword.equals(password); // Compare passwords (use hashing in production)
                } else {
                    return false; // No user found with the given email
                }
            }

        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            return false;
        }
    }

    // Register a new user
    public boolean register(String firstname, String lastname, String email, String password, String userRole) {
        String query = "INSERT INTO users (firstname, lastname, email, passwrd, user_role) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, firstname);
            pstmt.setString(2, lastname);
            pstmt.setString(3, email);
            pstmt.setString(4, password); // Note: Encrypt this password in a real-world app
            pstmt.setString(5, userRole);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // Returns true if the user was registered successfully

        } catch (SQLException e) {
            System.err.println("Error during registration: " + e.getMessage());
            return false;
        }
    }

    // Retrieve user details by email for session management
    public User getUserByEmail(String email) {
        String query = "SELECT * FROM users WHERE email = ?";
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
    
            // Set the email parameter in the query
            pstmt.setString(1, email);
    
            try (ResultSet rs = pstmt.executeQuery()) {
                // Check if the result set contains a row
                if (rs.next()) {
                    // Create a User object and populate it with the database values
                    User user = new User();
                    user.setId(rs.getInt("id"));                 // Get the 'id' column value
                    user.setFirstname(rs.getString("firstname")); // Get the 'firstname' column value
                    user.setLastname(rs.getString("lastname"));   // Get the 'lastname' column value
                    user.setEmail(rs.getString("email"));         // Get the 'email' column value
                    user.setPasswrd(rs.getString("passwrd"));    // Get the 'passwrd' column value (store hashed password in production)
                    user.setUserRole(rs.getString("user_role"));  // Get the 'user_role' column value
                    return user; // Return the populated User object
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        }
    
        return null; // Return null if no user was found
    }
    
}
