<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error - Testify</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/error.css">
</head>
<body>
    <div class="error-container">
        <div class="error-content">
            <div class="error-icon">⚠️</div>
            <h1>Oops! Something went wrong</h1>
            <p>${errorMessage != null ? errorMessage : 'An unexpected error occurred.'}</p>
            <div class="error-actions">
                <a href="${pageContext.request.contextPath}/dashboard" class="btn-primary">Back to Dashboard</a>
                <a href="javascript:history.back()" class="btn-secondary">Go Back</a>
            </div>
        </div>
    </div>
</body>
</html>