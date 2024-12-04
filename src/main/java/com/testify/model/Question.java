package com.testify.model;

import java.sql.Timestamp;

public class Question {
    private int id;
    private int examId;
    private String questionText;
    private String questionType;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor
    public Question(int id, int examId, String questionText, String questionType, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.examId = examId;
        this.questionText = questionText;
        this.questionType = questionType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Default constructor
    public Question() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", examId=" + examId +
                ", questionText='" + questionText + '\'' +
                ", questionType='" + questionType + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
