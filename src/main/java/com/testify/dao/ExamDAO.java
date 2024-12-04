package com.testify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testify.model.Exam;
import com.testify.util.DatabaseConnection;

public class ExamDAO {

    // Create a new exam in the database
    public boolean createExam(String title, String description, int createdBy) {
        String query = "INSERT INTO exams (title, description, created_by) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setInt(3, createdBy);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error creating exam: " + e.getMessage());
            return false;
        }
    }

    // Retrieve an exam by its ID
    public Exam getExamById(int examId) {
        String query = "SELECT * FROM exams WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, examId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Exam(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("created_by"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving exam: " + e.getMessage());
        }

        return null;
    }

    // Retrieve all exams created by a specific user
    public List<Exam> getExamsByCreator(int userId) {
        String query = "SELECT * FROM exams WHERE created_by = ?";
        List<Exam> exams = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    exams.add(new Exam(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getInt("created_by"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving exams: " + e.getMessage());
        }

        return exams;
    }

    // Update an existing exam in the database
    public boolean updateExam(int examId, String title, String description) {
        String query = "UPDATE exams SET title = ?, description = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setInt(3, examId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating exam: " + e.getMessage());
            return false;
        }
    }

    // Delete an exam by its ID
    public boolean deleteExam(int examId) {
        String query = "DELETE FROM exams WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, examId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting exam: " + e.getMessage());
            return false;
        }
    }
}
