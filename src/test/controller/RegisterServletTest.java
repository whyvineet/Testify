package com.testify.controller;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.servlet.http.*;

class RegisterServletTest {
    private RegisterServlet registerServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        registerServlet = new RegisterServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }

    @Test
    void testDoPostSuccess() throws Exception {
        when(request.getParameter("name")).thenReturn("John Doe");
        when(request.getParameter("email")).thenReturn("john@example.com");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getParameter("role")).thenReturn("student");
        
        registerServlet.doPost(request, response);
        verify(response).sendRedirect(contains("/login"));
    }

    @Test
    void testDoPostFailure() throws Exception {
        when(request.getParameter("name")).thenReturn("");
        registerServlet.doPost(request, response);
        verify(request).setAttribute(eq("error"), anyString());
    }
}