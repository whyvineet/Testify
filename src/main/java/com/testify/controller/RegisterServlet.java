package com.testify.controller;

import java.io.IOException;

import com.testify.Service.UserService;
import com.testify.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("/signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String firstname = request.getParameter("name").split(" ")[0];
        String lastname = request.getParameter("name").split(" ")[1];
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        try {
            User user = new User();
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setPasswrd(password);
            user.setUserRole(role);
            userService.registerUser(firstname, lastname, email, password, role);
            response.sendRedirect(request.getContextPath() + "/login");
        } catch (IOException e) {
            request.setAttribute("error", "Registration failed: " + e.getMessage());
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "An unexpected error occurred: " + e.getMessage());
            request.getRequestDispatcher("/signup.jsp").forward(request, response);
        }
    }
}