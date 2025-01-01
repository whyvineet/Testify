package com.testify.controller;

import java.io.IOException;
import java.util.List;

import com.testify.Service.ExamService;
import com.testify.model.Exam;
import com.testify.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private final ExamService examService = new ExamService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        User user = (User) session.getAttribute("user");
        
        if ("educator".equals(user.getUserRole())) {
            List<Exam> createdExams = examService.getExamsByCreator(user.getId());
            request.setAttribute("totalExams", createdExams.size());
            request.setAttribute("totalStudents", examService.getTotalStudents());
            request.setAttribute("averageScore", examService.getAverageScore(user.getId()));
        } else {
            List<Exam> availableExams = examService.getAvailableExams();
            request.setAttribute("recentExams", examService.getRecentExamsByStudent(user.getId()));
            request.setAttribute("availableExams", availableExams);
        }

        request.getRequestDispatcher("/dashboard.jsp").forward(request, response);
    }
}