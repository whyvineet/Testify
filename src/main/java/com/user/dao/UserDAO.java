package com.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.user.model.User;

public class UserDAO {
    private String jdbcURL = "jdbc:mysql://localhost:30006/testifydb";
    private String jdbcUserName = "root";
    private String jdbcPassword = "root";

    private static final String INSERT_USER_SQL = "INSERT INTO users (firstname, lastname, email, passwrd, user_role, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_SQL = "DELETE FROM users WHERE id = ?";
    private static final String UPDATE_USER_SQL = "UPDATE users SET firstname = ?, lastname = ?, email = ?, passwrd = ?, user_role = ?, updated_at = ? WHERE id = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcURL, jdbcUserName, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPasswrd());
            preparedStatement.setString(5, user.getUserRole());
            preparedStatement.setTimestamp(6, user.getCreatedAt());
            preparedStatement.setTimestamp(7, user.getUpdatedAt());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User selectUser(int id) {
        User user = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String passwrd = rs.getString("passwrd");
                String userRole = rs.getString("user_role");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                user = new User(id, firstname, lastname, email, passwrd, userRole, createdAt, updatedAt);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<User> selectAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String email = rs.getString("email");
                String passwrd = rs.getString("passwrd");
                String userRole = rs.getString("user_role");
                Timestamp createdAt = rs.getTimestamp("created_at");
                Timestamp updatedAt = rs.getTimestamp("updated_at");
                users.add(new User(id, firstname, lastname, email, passwrd, userRole, createdAt, updatedAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USER_SQL)) {
            statement.setString(1, user.getFirstname());
            statement.setString(2, user.getLastname());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPasswrd());
            statement.setString(5, user.getUserRole());
            statement.setTimestamp(6, user.getUpdatedAt());
            statement.setInt(7, user.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_USER_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
