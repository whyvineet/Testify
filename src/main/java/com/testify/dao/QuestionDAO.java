package com.testify.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.testify.model.Question;
import com.testify.util.DatabaseConnection;

public class QuestionDAO {

    // Create a new question for an exam
    public boolean createQuestion(int examId, String questionText, String questionType) {
        String query = "INSERT INTO questions (exam_id, question_text, question_type) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, examId);
            pstmt.setString(2, questionText);
            pstmt.setString(3, questionType);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error creating question: " + e.getMessage());
            return false;
        }
    }

    // Retrieve a question by its ID
    public Question getQuestionById(int questionId) {
        String query = "SELECT * FROM questions WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, questionId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Question(
                        rs.getInt("id"),
                        rs.getInt("exam_id"),
                        rs.getString("question_text"),
                        rs.getString("question_type"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving question: " + e.getMessage());
        }

        return null;
    }

    // Retrieve all questions for a specific exam
    public List<Question> getQuestionsByExam(int examId) {
        String query = "SELECT * FROM questions WHERE exam_id = ?";
        List<Question> questions = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, examId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    questions.add(new Question(
                        rs.getInt("id"),
                        rs.getInt("exam_id"),
                        rs.getString("question_text"),
                        rs.getString("question_type"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                    ));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving questions: " + e.getMessage());
        }

        return questions;
    }

    // Update an existing question
    public boolean updateQuestion(int questionId, String questionText, String questionType) {
        String query = "UPDATE questions SET question_text = ?, question_type = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, questionText);
            pstmt.setString(2, questionType);
            pstmt.setInt(3, questionId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error updating question: " + e.getMessage());
            return false;
        }
    }

    // Delete a question by its ID
    public boolean deleteQuestion(int questionId) {
        String query = "DELETE FROM questions WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, questionId);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting question: " + e.getMessage());
            return false;
        }
    }
}
