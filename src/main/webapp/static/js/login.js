document.addEventListener('DOMContentLoaded', function() {
    // Password toggle visibility
    const passwordInput = document.getElementById('password');
    const passwordToggle = document.querySelector('.toggle-password');

    if (passwordToggle && passwordInput) {
        passwordToggle.addEventListener('click', function() {
            const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
            passwordInput.setAttribute('type', type);
            
            // Toggle eye icon
            this.classList.toggle('fa-eye');
            this.classList.toggle('fa-eye-slash');
        });
    }

    // Form validation
    const loginForm = document.getElementById('loginForm');
    
    if (loginForm) {
        loginForm.addEventListener('submit', function(e) {
            const emailInput = document.getElementById('email');
            const passwordInput = document.getElementById('password');
            let isValid = true;

            // Email validation
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailRegex.test(emailInput.value.trim())) {
                isValid = false;
                showError(emailInput, 'Please enter a valid email address');
            } else {
                clearError(emailInput);
            }

            // Password validation
            if (passwordInput.value.trim().length < 8) {
                isValid = false;
                showError(passwordInput, 'Password must be at least 8 characters long');
            } else {
                clearError(passwordInput);
            }

            if (!isValid) {
                e.preventDefault();
            }
        });
    }

    // Social login handlers (placeholders)
    const googleLoginBtn = document.querySelector('.social-btn.google');
    const microsoftLoginBtn = document.querySelector('.social-btn.microsoft');

    if (googleLoginBtn) {
        googleLoginBtn.addEventListener('click', function() {
            // Placeholder for Google OAuth logic
            alert('Google Login: Redirecting to authentication...');
        });
    }

    if (microsoftLoginBtn) {
        microsoftLoginBtn.addEventListener('click', function() {
            // Placeholder for Microsoft OAuth logic
            alert('Microsoft Login: Redirecting to authentication...');
        });
    }

    // Helper functions for error handling
    function showError(input, message) {
        // Remove any existing error
        clearError(input);

        // Create error message
        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.innerText = message;
        
        // Insert error message after input
        input.classList.add('input-error');
        input.parentNode.insertBefore(errorDiv, input.nextSibling);
    }

    function clearError(input) {
        input.classList.remove('input-error');
        
        // Remove error message if it exists
        const errorMessage = input.parentNode.querySelector('.error-message');
        if (errorMessage) {
            errorMessage.remove();
        }
    }

    // Remember me functionality
    const rememberCheckbox = document.getElementById('remember');
    const emailInput = document.getElementById('email');

    // Check if there's a saved email
    const savedEmail = localStorage.getItem('rememberedEmail');
    if (savedEmail) {
        emailInput.value = savedEmail;
        rememberCheckbox.checked = true;
    }

    // Save email if remember me is checked
    loginForm.addEventListener('submit', function() {
        if (rememberCheckbox.checked) {
            localStorage.setItem('rememberedEmail', emailInput.value);
        } else {
            localStorage.removeItem('rememberedEmail');
        }
    });
});

// Optional: Add error logging or analytics
window.addEventListener('error', function(event) {
    console.error('Unhandled error:', event.error);
    // Optional: Send error to logging service
});