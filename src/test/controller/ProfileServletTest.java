package com.testify.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.servlet.http.*;
import com.testify.model.User;

class ProfileServletTest {
    private ProfileServlet profileServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private User user;

    @BeforeEach
    void setUp() {
        profileServlet = new ProfileServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        user = new User();
        user.setId(1);
        when(request.getSession(false)).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
    }

    @Test
    void testDoGetSuccess() throws Exception {
        profileServlet.doGet(request, response);
        verify(request).setAttribute(eq("userProfile"), any(User.class));
    }

    @Test
    void testDoPostSuccess() throws Exception {
        when(request.getParameter("firstname")).thenReturn("John");
        when(request.getParameter("lastname")).thenReturn("Doe");
        when(request.getParameter("email")).thenReturn("john@example.com");
        
        profileServlet.doPost(request, response);
        verify(request).setAttribute(eq("success"), anyString());
    }
}