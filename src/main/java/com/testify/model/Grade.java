package com.testify.model;

import java.sql.Timestamp;

public class Grade {
    private int id;
    private int studentId;
    private int examId;
    private double grade;
    private String status;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Constructor
    public Grade(int id, int studentId, int examId, double grade, String status, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.studentId = studentId;
        this.examId = examId;
        this.grade = grade;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Default constructor
    public Grade() {
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "Grade{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", examId=" + examId +
                ", grade=" + grade +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
