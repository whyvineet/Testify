package com.testify.dao;

import com.testify.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/testifydb";  // Ensure correct port and database
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    // SQL Queries
    private static final String INSERT_USER_SQL = "INSERT INTO users (firstname, lastname, email, passwrd, user_role, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET firstname = ?, lastname = ?, email = ?, passwrd = ?, user_role = ?, updated_at = ? WHERE id = ?";

    // Establishing database connection
    protected Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Database connection failed", e);
            return null;
        }
    }

    // Insert user into the database
    public boolean insertUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPasswrd(), BCrypt.gensalt()); // Hash password
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, hashedPassword);  // Storing hashed password
            preparedStatement.setString(5, user.getUserRole());
            preparedStatement.setTimestamp(6, user.getCreatedAt());
            preparedStatement.setTimestamp(7, user.getUpdatedAt());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error inserting user", e);
            throw e;
        }
    }

    // Select user by ID
    public User selectUser(int id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {

            preparedStatement.setInt(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error retrieving user by ID", e);
        }
        return null;
    }

    // Select user by Email
    public User selectUserByEmail(String email) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL)) {

            preparedStatement.setString(1, email);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToUser(rs);
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error retrieving user by email", e);
        }
        return null;
    }

    // Select all users
    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = preparedStatement.executeQuery()) {

            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error retrieving all users", e);
        }
        return users;
    }

    // Update user in the database
    public boolean updateUser(User user) throws SQLException {
        String hashedPassword = BCrypt.hashpw(user.getPasswrd(), BCrypt.gensalt()); // Hash password
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, hashedPassword);  // Storing hashed password
            preparedStatement.setString(5, user.getUserRole());
            preparedStatement.setTimestamp(6, user.getUpdatedAt());
            preparedStatement.setInt(7, user.getId());

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error updating user", e);
            throw e;
        }
    }

    // Delete user by ID
    public boolean deleteUser(int id) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {

            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, "Error deleting user", e);
            throw e;
        }
    }

    // Helper method to map ResultSet to User object
    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String email = rs.getString("email");
        String passwrd = rs.getString("passwrd");
        String userRole = rs.getString("user_role");
        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp updatedAt = rs.getTimestamp("updated_at");

        return new User(id, firstname, lastname, email, passwrd, userRole, createdAt, updatedAt);
    }
}
