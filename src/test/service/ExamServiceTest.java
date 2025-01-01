package com.testify.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.testify.Service.ExamService;
import com.testify.model.Exam;

class ExamServiceTest {
    private ExamService examService;

    @BeforeEach
    void setUp() {
        examService = new ExamService();
    }

    @Test
    void testCreateExam() {
        assertTrue(examService.createExam("Test Exam", "Description", 1));
    }

    @Test
    void testGetExamById() {
        Exam exam = examService.getExamById(1);
        assertNotNull(exam);
        assertEquals("Test Exam", exam.getTitle());
    }

    @Test
    void testGetExamsByCreator() {
        List<Exam> exams = examService.getExamsByCreator(1);
        assertNotNull(exams);
        assertFalse(exams.isEmpty());
    }

    @Test
    void testUpdateExam() {
        assertTrue(examService.updateExam(1, "Updated Exam", "New Description"));
    }
}