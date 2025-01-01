package com.testify.controller;

import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
@ExtendWith(MockitoExtension.class)

class AuthenticationControllerTest {
    private AuthenticationController authController;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;

    @BeforeEach
    void setUp() {
        authController = new AuthenticationController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
    }

    @Test
    void testDoPostSuccess() throws Exception {
        when(request.getParameter("email")).thenReturn("test@example.com");
        when(request.getParameter("password")).thenReturn("password");
        
        authController.doPost(request, response);
        verify(response).sendRedirect(contains("dashboard.jsp"));
    }

    @Test
    void testDoPostFailure() throws Exception {
        when(request.getParameter("email")).thenReturn("wrong@example.com");
        when(request.getParameter("password")).thenReturn("wrongpassword");
        
        authController.doPost(request, response);
        verify(request).setAttribute(eq("error"), anyString());
    }
}