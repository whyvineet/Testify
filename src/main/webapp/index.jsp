<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Testify - Revolutionize Online Assessments</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/styles.css">
    <meta name="description" content="Testify: Advanced online examination platform with secure proctoring, instant grading, and comprehensive analytics">
    <link rel="icon" href="${pageContext.request.contextPath}/static/images/favicon.ico" type="image/x-icon">
</head>
<body>
    <header>
        <nav>
            <div class="logo">
                <img src="${pageContext.request.contextPath}/static/images/testify-logo.svg" alt="Testify Logo">
            </div>
            <div class="nav-links">
                <a href="#features">Features</a>
                <a href="#solutions">Solutions</a>
                <a href="#integrations">Integrations</a>
                <a href="#pricing">Pricing</a>
                <a href="${pageContext.request.contextPath}/login" class="btn btn-outline">Log in</a>
                <a href="${pageContext.request.contextPath}/signup" class="btn btn-primary">Start Free Trial</a>
            </div>
        </nav>
    </header>

    <main>
        <section class="hero">
            <div class="hero-content">
                <h1>Reimagine Online Examinations</h1>
                <p>Empower educators with cutting-edge assessment technology. Advanced proctoring, seamless exam creation, and intelligent analytics—all in one platform.</p>
                <div class="hero-buttons">
                    <a href="${pageContext.request.contextPath}/signup" class="btn btn-primary btn-large">Start Free Trial</a>
                    <a href="#demo" class="btn btn-outline btn-large">Watch Demo</a>
                </div>
                <div class="hero-badges">
                    <span>🏆 Best EdTech Platform 2024</span>
                    <span>✅ GDPR Compliant</span>
                </div>
            </div>
            <div class="hero-visual">
                <img src="${pageContext.request.contextPath}/static/images/platform-overview.webp" alt="Testify Platform Dashboard" class="hero-image">
                <div class="visual-overlay">
                    <div class="stat-bubble">10,000+ Active Users</div>
                    <div class="stat-bubble">98% Satisfaction Rate</div>
                </div>
            </div>
        </section>

        <section id="features" class="features">
            <h2>Powerful Features for Modern Assessment</h2>
            <div class="features-grid">
                <div class="feature-card">
                    <div class="feature-icon">🎨</div>
                    <h3>Flexible Exam Design</h3>
                    <p>Create dynamic exams with multiple question types, randomization, and adaptive testing. Support for text, multiple-choice, coding challenges, and more.</p>
                </div>
                <div class="feature-card">
                    <div class="feature-icon">🕵️</div>
                    <h3>Advanced Proctoring</h3>
                    <p>AI-powered identity verification, browser lockdown, real-time video monitoring, and suspicious activity detection to ensure exam integrity.</p>
                </div>
                <div class="feature-card">
                    <div class="feature-icon">📊</div>
                    <h3>Intelligent Analytics</h3>
                    <p>Comprehensive performance dashboards, predictive learning insights, and detailed individual and comparative reports.</p>
                </div>
                <div class="feature-card">
                    <div class="feature-icon">🤝</div>
                    <h3>Seamless Integrations</h3>
                    <p>Connect effortlessly with popular LMS platforms, single sign-on solutions, and third-party educational tools.</p>
                </div>
            </div>
        </section>

        <section class="testimonials">
            <h2>What Our Users Say</h2>
            <div class="testimonial-slider">
                <div class="testimonial-card">
                    <p>"Testify transformed our remote examination process. The security features are unparalleled."</p>
                    <div class="testimonial-author">
                        <img src="${pageContext.request.contextPath}/static/images/prof-sarah.jpg" alt="Sarah Thompson">
                        <div>
                            <strong>Sarah Thompson</strong>
                            <span>Dean, Online Learning Institute</span>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <section class="cta">
            <div class="cta-content">
                <h2>Transform Your Assessment Strategy</h2>
                <p>Join leading institutions in revolutionizing online examinations. No credit card required.</p>
                <a href="${pageContext.request.contextPath}/signup" class="btn btn-primary btn-large">Start Free Trial</a>
            </div>
        </section>
    </main>

    <footer>
        <div class="footer-content">
            <div class="footer-section">
                <h4>Platform</h4>
                <ul>
                    <li><a href="#">Features</a></li>
                    <li><a href="#">Security</a></li>
                    <li><a href="#">Pricing</a></li>
                    <li><a href="#">Enterprise Solutions</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h4>Company</h4>
                <ul>
                    <li><a href="#">About Us</a></li>
                    <li><a href="#">Careers</a></li>
                    <li><a href="#">Press</a></li>
                    <li><a href="#">Contact</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h4>Resources</h4>
                <ul>
                    <li><a href="#">Help Center</a></li>
                    <li><a href="#">Documentation</a></li>
                    <li><a href="#">Blog</a></li>
                    <li><a href="#">Webinars</a></li>
                </ul>
            </div>
            <div class="footer-section">
                <h4>Legal</h4>
                <ul>
                    <li><a href="#">Privacy Policy</a></li>
                    <li><a href="#">Terms of Service</a></li>
                    <li><a href="#">GDPR Compliance</a></li>
                    <li><a href="#">Security Policy</a></li>
                </ul>
            </div>
        </div>
        <div class="footer-bottom">
            <p>&copy; 2024 Testify. All Rights Reserved.</p>
        </div>
    </footer>

    <script src="${pageContext.request.contextPath}/static/js/main.js"></script>
</body>
</html>