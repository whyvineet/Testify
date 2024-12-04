package com.testify.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testify.Service.ExamService;

@WebServlet("/createExam")
public class CreateExamServlet extends HttpServlet {

    private ExamService examService;

    @Override
    public void init() throws ServletException {
        examService = new ExamService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve data from the form
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Integer createdBy = (Integer) request.getSession().getAttribute("userId"); // Must be set in session at login

        if (createdBy == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Call service to create an exam
        if (examService.createExam(title, description, createdBy)) {
            response.sendRedirect("dashboard.jsp?message=Exam created successfully");
        } else {
            request.setAttribute("error", "Failed to create exam. Please try again.");
            request.getRequestDispatcher("createExam.jsp").forward(request, response);
        }
    }
}
