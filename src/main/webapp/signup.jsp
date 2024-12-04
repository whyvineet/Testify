<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create Account - Testify</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/signup.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="signup-container">
        <div class="signup-wrapper">
            <div class="signup-logo">
                <img src="${pageContext.request.contextPath}/static/images/testify-logo.svg" alt="Testify Logo">
            </div>
            
            <form id="signupForm" class="signup-form" action="${pageContext.request.contextPath}/signup" method="post" novalidate>
                <h2>Create Your Testify Account</h2>
                <p class="subtitle">Join our platform and enhance your learning experience</p>

                <div class="form-group">
                    <label for="signup-name">Full Name</label>
                    <div class="input-wrapper">
                        <i class="fas fa-user input-icon"></i>
                        <input 
                            type="text" 
                            id="signup-name" 
                            name="name" 
                            required 
                            placeholder="Enter your full name"
                            minlength="2"
                            maxlength="50"
                        >
                        <span class="error-message" id="name-error"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="signup-email">Email Address</label>
                    <div class="input-wrapper">
                        <i class="fas fa-envelope input-icon"></i>
                        <input 
                            type="email" 
                            id="signup-email" 
                            name="email" 
                            required 
                            placeholder="Enter your email"
                        >
                        <span class="error-message" id="email-error"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label>Account Type</label>
                    <div class="role-selection">
                        <div class="role-option">
                            <input 
                                type="radio" 
                                id="role-student" 
                                name="role" 
                                value="student" 
                                required
                            >
                            <label for="role-student">
                                <i class="fas fa-graduation-cap"></i> Student
                            </label>
                        </div>
                        <div class="role-option">
                            <input 
                                type="radio" 
                                id="role-teacher" 
                                name="role" 
                                value="educator" 
                                required
                            >
                            <label for="role-teacher">
                                <i class="fas fa-chalkboard-teacher"></i> Educator
                            </label>
                        </div>
                    </div>
                    <span class="error-message" id="role-error"></span>
                </div>

                <div class="form-group">
                    <label for="signup-institution">Institution</label>
                    <div class="input-wrapper">
                        <i class="fas fa-university input-icon"></i>
                        <input 
                            type="text" 
                            id="signup-institution" 
                            name="institution" 
                            placeholder="Enter your institution name"
                            maxlength="100"
                        >
                    </div>
                </div>

                <div class="form-group">
                    <label for="signup-password">Password</label>
                    <div class="input-wrapper">
                        <i class="fas fa-lock input-icon"></i>
                        <input 
                            type="password" 
                            id="signup-password" 
                            name="password" 
                            required 
                            placeholder="Create a strong password"
                            minlength="8"
                            maxlength="30"
                        >
                        <span class="toggle-password" onclick="togglePasswordVisibility('signup-password')">
                            <i class="fas fa-eye-slash" id="password-toggle-icon"></i>
                        </span>
                        <span class="error-message" id="password-error"></span>
                    </div>
                    <div class="password-strength">
                        <div class="strength-bar" id="password-strength"></div>
                        <small id="password-strength-text"></small>
                    </div>
                </div>

                <div class="form-group">
                    <label for="signup-confirm-password">Confirm Password</label>
                    <div class="input-wrapper">
                        <i class="fas fa-lock input-icon"></i>
                        <input 
                            type="password" 
                            id="signup-confirm-password" 
                            name="confirmPassword" 
                            required 
                            placeholder="Confirm your password"
                            minlength="8"
                            maxlength="30"
                        >
                        <span class="toggle-password" onclick="togglePasswordVisibility('signup-confirm-password')">
                            <i class="fas fa-eye-slash" id="confirm-password-toggle-icon"></i>
                        </span>
                        <span class="error-message" id="confirm-password-error"></span>
                    </div>
                </div>

                <button type="submit" class="btn-signup" disabled>
                    Create Account
                    <i class="fas fa-arrow-right"></i>
                </button>

                <div class="signup-footer">
                    <p>Already have an account? <a href="${pageContext.request.contextPath}/login">Log in</a></p>
                </div>
            </form>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/static/js/signup.js"></script>
</body>
</html>