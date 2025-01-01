<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile - Testify</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/profile.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <div class="profile-container">
        <c:if test="${not empty success}">
            <div class="alert alert-success">${success}</div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-error">${error}</div>
        </c:if>

        <div class="profile-card">
            <div class="profile-header">
                <div class="profile-avatar">
                    <img src="${pageContext.request.contextPath}/static/images/default-avatar.png" alt="Profile Picture">
                </div>
                <h1>${userProfile.firstname} ${userProfile.lastname}</h1>
                <span class="user-role">${userProfile.userRole}</span>
            </div>

            <form class="profile-form" action="${pageContext.request.contextPath}/profile" method="post">
                <div class="form-group">
                    <label for="firstname">First Name</label>
                    <input type="text" id="firstname" name="firstname" value="${userProfile.firstname}" required>
                </div>

                <div class="form-group">
                    <label for="lastname">Last Name</label>
                    <input type="text" id="lastname" name="lastname" value="${userProfile.lastname}" required>
                </div>

                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" value="${userProfile.email}" required>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Update Profile</button>
                    <a href="${pageContext.request.contextPath}/dashboard" class="btn btn-secondary">Back to Dashboard</a>
                </div>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/static/js/profile.js"></script>
</body>
</html>