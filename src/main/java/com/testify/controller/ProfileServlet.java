package com.testify.controller;

import java.io.IOException;

import com.testify.Service.UserService;
import com.testify.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        request.setAttribute("userProfile", userService.getUserById(user.getId()));
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        user.setFirstname(request.getParameter("firstname"));
        user.setLastname(request.getParameter("lastname"));
        user.setEmail(request.getParameter("email"));
        
        try {
            userService.updateUser(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getPasswrd(), user.getUserRole());
            session.setAttribute("user", user);
            request.setAttribute("success", "Profile updated successfully");
        } catch (Exception e) {
            request.setAttribute("error", "Update failed: " + e.getMessage());
        }
        
        request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }
}