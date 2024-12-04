<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard - Testify</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <aside class="sidebar">
            <div class="sidebar-content">
                <div class="logo">
                    <img src="${pageContext.request.contextPath}/static/images/testify-logo.svg" alt="Testify Logo">
                </div>
                <nav>
                    <ul>
                        <li class="nav-item active" data-section="dashboard">
                            <a href="#dashboard">
                                <i class="fas fa-home"></i>
                                <span>Dashboard</span>
                            </a>
                        </li>
                        <li class="nav-item" data-section="exams">
                            <a href="#exams">
                                <i class="fas fa-file-alt"></i>
                                <span>Exams</span>
                            </a>
                        </li>
                        <li class="nav-item" data-section="results">
                            <a href="#results">
                                <i class="fas fa-chart-bar"></i>
                                <span>Results</span>
                            </a>
                        </li>
                        <li class="nav-item" data-section="analytics">
                            <a href="#analytics">
                                <i class="fas fa-analytics"></i>
                                <span>Analytics</span>
                            </a>
                        </li>
                        <li class="nav-item" data-section="profile">
                            <a href="#profile">
                                <i class="fas fa-user-circle"></i>
                                <span>Profile</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>

            <div class="user-info">
                <div class="user-avatar">
                    <img src="${pageContext.request.contextPath}/static/images/user-avatar.png" alt="User Avatar">
                    <div class="user-status online"></div>
                </div>
                <div class="user-details">
                    <span class="user-name">${user.firstname} ${user.lastname}</span>
                    <span class="user-role">${user.userRole}</span>
                </div>
                <button class="btn-logout" onclick="logout()">
                    <i class="fas fa-sign-out-alt"></i>
                </button>
            </div>
        </aside>

        <main class="main-content">
            <header class="dashboard-header">
                <div class="header-left">
                    <h1>Welcome, ${user.firstname}!</h1>
                    <p class="greeting-time" id="greeting-time">Good morning</p>
                </div>
                <div class="header-right">
                    <div class="search-bar">
                        <input type="text" placeholder="Search..." id="dashboard-search">
                        <i class="fas fa-search"></i>
                    </div>
                    <div class="header-actions">
                        <c:if test="${user.userRole == 'educator'}">
                            <button class="btn btn-primary" onclick="openCreateExamModal()">
                                <i class="fas fa-plus-circle"></i> Create Exam
                            </button>
                        </c:if>
                        <c:if test="${user.userRole == 'student'}">
                            <button class="btn btn-primary" onclick="openAvailableExamsModal()">
                                <i class="fas fa-list-alt"></i> Available Exams
                            </button>
                        </c:if>
                    </div>
                </div>
            </header>

            <section class="dashboard-overview">
                <c:if test="${user.userRole == 'educator'}">
                    <div class="stats-grid">
                        <div class="stat-card stat-exams">
                            <div class="stat-icon">
                                <i class="fas fa-file-alt"></i>
                            </div>
                            <div class="stat-content">
                                <h3>Total Exams</h3>
                                <div class="stat-number">${totalExams}</div>
                                <div class="stat-trend positive">
                                    <i class="fas fa-arrow-up"></i> +${examGrowth}% this month
                                </div>
                            </div>
                        </div>
                        <div class="stat-card stat-students">
                            <div class="stat-icon">
                                <i class="fas fa-users"></i>
                            </div>
                            <div class="stat-content">
                                <h3>Total Students</h3>
                                <div class="stat-number">${totalStudents}</div>
                                <div class="stat-trend positive">
                                    <i class="fas fa-arrow-up"></i> +${studentGrowth}% this month
                                </div>
                            </div>
                        </div>
                        <div class="stat-card stat-performance">
                            <div class="stat-icon">
                                <i class="fas fa-chart-line"></i>
                            </div>
                            <div class="stat-content">
                                <h3>Average Score</h3>
                                <div class="stat-number">${averageScore}%</div>
                                <div class="stat-trend ${averageScore > 75 ? 'positive' : 'negative'}">
                                    <i class="fas ${averageScore > 75 ? 'fa-arrow-up' : 'fa-arrow-down'}"></i>
                                    ${averageScoreTrend}% from last month
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>

                <c:if test="${user.userRole == 'student'}">
                    <div class="student-overview">
                        <div class="recent-exams">
                            <h2>Recent Exams</h2>
                            <div class="exam-list">
                                <c:forEach items="${recentExams}" var="exam">
                                    <div class="exam-item">
                                        <div class="exam-details">
                                            <h3>${exam.name}</h3>
                                            <span>${exam.subject}</span>
                                        </div>
                                        <div class="exam-score ${exam.score >= 75 ? 'pass' : 'fail'}">
                                            ${exam.score}%
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="performance-chart" id="student-performance-chart"></div>
                    </div>
                </c:if>
            </section>
        </main>
    </div>

    <!-- Modals and additional components will be added here -->

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/dashboard.js"></script>
</body>
</html>