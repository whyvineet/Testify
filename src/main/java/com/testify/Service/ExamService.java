package com.testify.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testify.model.Exam;
import com.testify.util.DatabaseConnection;

public class ExamService {

    // Create a new exam
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

    // Retrieve an exam by ID
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

    // Update an existing exam
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

    // Delete an exam by ID
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

    // Get total number of students
    public int getTotalStudents() {
        String query = "SELECT COUNT(*) AS total_students FROM students";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("total_students");
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving total students: " + e.getMessage());
        }

        return 0;
    }
    // Get average score for an exam
    public double getAverageScore(int examId) {
        String query = "SELECT AVG(score) AS average_score FROM exam_results WHERE exam_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, examId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("average_score");
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving average score: " + e.getMessage());
        }

        return 0.0;
    }

    // Get all available exams
    public List<Exam> getAvailableExams() {
        String query = "SELECT * FROM exams";
        List<Exam> exams = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

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

        } catch (SQLException e) {
            System.err.println("Error retrieving available exams: " + e.getMessage());
        }

        return exams;
    }

    // Get recent exams taken by a student
    public List<Exam> getRecentExamsByStudent(int studentId) {
        String query = "SELECT e.* FROM exams e " +
                       "JOIN exam_results er ON e.id = er.exam_id " +
                       "WHERE er.student_id = ? " +
                       "ORDER BY er.taken_at DESC";
        List<Exam> exams = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, studentId);

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
            System.err.println("Error retrieving recent exams: " + e.getMessage());
        }

        return exams;
    }

}
