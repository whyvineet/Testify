// Form validation and UI interactions
document.addEventListener('DOMContentLoaded', function() {
    const profileForm = document.querySelector('.profile-form');
    const inputs = profileForm.querySelectorAll('input[required]');
    
    // Form validation
    function validateForm() {
        let isValid = true;
        inputs.forEach(input => {
            if (!validateInput(input)) {
                isValid = false;
            }
        });
        return isValid;
    }
    
    function validateInput(input) {
        const value = input.value.trim();
        
        switch(input.type) {
            case 'email':
                if (!isValidEmail(value)) {
                    showError(input, 'Please enter a valid email address');
                    return false;
                }
                break;
            case 'text':
                if (value.length < 2) {
                    showError(input, 'This field must be at least 2 characters long');
                    return false;
                }
                break;
        }
        
        clearError(input);
        return true;
    }
    
    function isValidEmail(email) {
        return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
    }
    
    function showError(input, message) {
        clearError(input);
        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.textContent = message;
        input.parentNode.appendChild(errorDiv);
        input.classList.add('error');
    }
    
    function clearError(input) {
        const errorDiv = input.parentNode.querySelector('.error-message');
        if (errorDiv) {
            errorDiv.remove();
        }
        input.classList.remove('error');
    }
    
    // Input event listeners
    inputs.forEach(input => {
        input.addEventListener('blur', () => validateInput(input));
        input.addEventListener('input', () => validateInput(input));
    });
    
    // Form submission
    profileForm.addEventListener('submit', function(e) {
        if (!validateForm()) {
            e.preventDefault();
            return;
        }
        
        // Show loading state
        const submitButton = this.querySelector('button[type="submit"]');
        submitButton.disabled = true;
        submitButton.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Updating...';
    });
    
    // Alert dismissal
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.opacity = '0';
            setTimeout(() => alert.remove(), 300);
        }, 5000);
    });
});