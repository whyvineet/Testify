package com.testify.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.testify.model.Exam;
import java.util.List;

class ExamDAOTest {
    private ExamDAO examDAO;

    @BeforeEach
    void setUp() {
        examDAO = new ExamDAO();
    }

    @Test
    void testCreateExam() {
        assertTrue(examDAO.createExam("Test Exam", "Description", 1));
    }

    @Test
    void testGetExamById() {
        Exam exam = examDAO.getExamById(1);
        assertNotNull(exam);
        assertEquals("Test Exam", exam.getTitle());
    }

    @Test
    void testUpdateExam() {
        assertTrue(examDAO.updateExam(1, "Updated Exam", "New Description"));
    }

    @Test
    void testDeleteExam() {
        assertTrue(examDAO.deleteExam(1));
    }
}