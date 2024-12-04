package com.testify.controller;

import com.testify.Service.AuthenticationService;
import com.testify.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {

    private AuthenticationService authService;

    @Override
    public void init() throws ServletException {
        authService = new AuthenticationService();
    }

    // Handle user login
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Authenticate user
        if (authService.login(email, password)) {
            // On successful login, retrieve user data and store it in session
            User user = authService.getUserByEmail(email);
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getUserRole());
            session.setAttribute("firstname", user.getFirstname());

            // Redirect to appropriate page based on role
            if ("educator".equals(user.getUserRole())) {
                response.sendRedirect("dashboard.jsp");
            } else {
                response.sendRedirect("studentDashboard.jsp");
            }
        } else {
            // Invalid login credentials
            request.setAttribute("error", "Invalid email or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    // Handle user registration
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show the registration page
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String userRole = request.getParameter("userRole");

        // Register a new user
        if (authService.register(firstname, lastname, email, password, userRole)) {
            response.sendRedirect("login.jsp");
        } else {
            request.setAttribute("error", "Registration failed, please try again.");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
    }

    // Handle user logout
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate the session on logout
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
}
