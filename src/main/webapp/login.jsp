<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Testify</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="login-container">
        <div class="login-wrapper">
            <div class="login-left">
                <div class="login-logo">
                    <img src="${pageContext.request.contextPath}/static/images/testify-logo.svg" alt="Testify Logo">
                </div>
                <h1>Welcome Back</h1>
                <p>Log in to continue your assessment journey</p>
                
                <form id="loginForm" action="${pageContext.request.contextPath}/login" method="post">
                    <div class="form-group">
                        <label for="email">Email Address</label>
                        <div class="input-wrapper">
                            <i class="fas fa-envelope"></i>
                            <input type="email" id="email" name="email" required placeholder="Enter your email">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">Password</label>
                        <div class="input-wrapper">
                            <i class="fas fa-lock"></i>
                            <input type="password" id="password" name="password" required placeholder="Enter your password">
                            <i class="fas fa-eye-slash toggle-password"></i>
                        </div>
                    </div>
                    
                    <div class="form-actions">
                        <label class="remember-me">
                            <input type="checkbox" name="remember" id="remember">
                            Remember me
                        </label>
                        <a href="${pageContext.request.contextPath}/forgot-password" class="forgot-password">Forgot Password?</a>
                    </div>
                    
                    <button type="submit" class="btn-login">Log In</button>
                    
                    <div class="social-login">
                        <p>Or continue with</p>
                        <div class="social-buttons">
                            <button type="button" class="social-btn google">
                                <i class="fab fa-google"></i> Google
                            </button>
                            <button type="button" class="social-btn microsoft">
                                <i class="fab fa-microsoft"></i> Microsoft
                            </button>
                        </div>
                    </div>
                </form>
                
                <div class="signup-link">
                    Don't have an account? <a href="${pageContext.request.contextPath}/signup">Sign Up</a>
                </div>
            </div>
            
            <div class="login-right">
                <div class="login-illustration">
                    <img src="${pageContext.request.contextPath}/static/images/login-illustration.svg" alt="Login Illustration">
                </div>
            </div>
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/static/js/login.js"></script>
</body>
</html>