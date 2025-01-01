package com.testify.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.testify.model.Question;
import java.util.List;

class QuestionDAOTest {
    private QuestionDAO questionDAO;

    @BeforeEach
    void setUp() {
        questionDAO = new QuestionDAO();
    }

    @Test
    void testCreateQuestion() {
        assertTrue(questionDAO.createQuestion(1, "Test Question?", "MCQ"));
    }

    @Test
    void testGetQuestionById() {
        Question question = questionDAO.getQuestionById(1);
        assertNotNull(question);
        assertEquals("Test Question?", question.getQuestionText());
    }

    @Test
    void testUpdateQuestion() {
        assertTrue(questionDAO.updateQuestion(1, "Updated Question?", "MCQ"));
    }

    @Test
    void testDeleteQuestion() {
        assertTrue(questionDAO.deleteQuestion(1));
    }
}