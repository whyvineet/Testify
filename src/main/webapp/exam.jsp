<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Exam - ${exam.title}</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/exam.css">
</head>
<body>
    <div class="exam-container">
        <header class="exam-header">
            <h1>${exam.title}</h1>
            <div class="exam-timer" id="examTimer"></div>
        </header>

        <form id="examForm" action="${pageContext.request.contextPath}/exam/submit" method="post">
            <input type="hidden" name="examId" value="${exam.id}">
            
            <c:forEach items="${questions}" var="question" varStatus="status">
                <div class="question-card">
                    <div class="question-header">
                        <span class="question-number">Question ${status.index + 1}</span>
                        <span class="question-type">${question.questionType}</span>
                    </div>
                    
                    <div class="question-content">
                        <p>${question.questionText}</p>
                        
                        <c:choose>
                            <c:when test="${question.questionType == 'multiple_choice'}">
                                <div class="options-list">
                                    <c:forEach items="${question.options}" var="option">
                                        <label class="option">
                                            <input type="radio" name="q${question.id}" value="${option.id}">
                                            <span class="option-text">${option.optionText}</span>
                                        </label>
                                    </c:forEach>
                                </div>
                            </c:when>
                            <c:when test="${question.questionType == 'essay'}">
                                <textarea name="q${question.id}" rows="5" placeholder="Enter your answer"></textarea>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </c:forEach>

            <div class="exam-actions">
                <button type="submit" class="btn-submit">Submit Exam</button>
            </div>
        </form>
    </div>

    <script src="${pageContext.request.contextPath}/static/js/exam.js"></script>
</body>
</html>