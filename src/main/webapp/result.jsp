<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Exam Results - Testify</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/result.css">
</head>
<body>
    <div class="result-container">
        <div class="result-header">
            <h1>Exam Results</h1>
            <div class="score-summary">
                <div class="score-circle ${grade.score >= 75 ? 'pass' : 'fail'}">
                    <span class="score-value">${grade.score}%</span>
                </div>
            </div>
        </div>

        <div class="result-details">
            <div class="detail-card">
                <h3>Exam Details</h3>
                <p><strong>Title:</strong> ${exam.title}</p>
                <p><strong>Date:</strong> ${grade.createdAt}</p>
                <p><strong>Duration:</strong> ${exam.duration} minutes</p>
            </div>

            <div class="performance-summary">
                <h3>Performance Summary</h3>
                <div class="metrics">
                    <div class="metric">
                        <span class="metric-label">Correct Answers</span>
                        <span class="metric-value">${correctAnswers}/${totalQuestions}</span>
                    </div>
                </div>
            </div>
        </div>

        <div class="result-actions">
            <a href="${pageContext.request.contextPath}/dashboard" class="btn-primary">Back to Dashboard</a>
            <a href="${pageContext.request.contextPath}/exam/review/${exam.id}" class="btn-secondary">Review Answers</a>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/static/js/result.js"></script>
</body>
</html>