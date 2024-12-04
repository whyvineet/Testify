document.addEventListener('DOMContentLoaded', function() {
    const signupForm = document.getElementById('signupForm');
    const nameInput = document.getElementById('signup-name');
    const emailInput = document.getElementById('signup-email');
    const passwordInput = document.getElementById('signup-password');
    const confirmPasswordInput = document.getElementById('signup-confirm-password');
    const submitButton = document.querySelector('.btn-signup');
    const passwordStrengthBar = document.getElementById('password-strength');
    const passwordStrengthText = document.getElementById('password-strength-text');

    // Form validation and error handling
    const validateName = () => {
        const nameError = document.getElementById('name-error');
        const nameValue = nameInput.value.trim();
        const nameRegex = /^[a-zA-Z\s]{2,50}$/;

        if (!nameValue) {
            nameError.textContent = 'Name is required';
            return false;
        } else if (!nameRegex.test(nameValue)) {
            nameError.textContent = 'Name must be 2-50 characters, letters only';
            return false;
        } else {
            nameError.textContent = '';
            return true;
        }
    };

    const validateEmail = () => {
        const emailError = document.getElementById('email-error');
        const emailValue = emailInput.value.trim();
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailValue) {
            emailError.textContent = 'Email is required';
            return false;
        } else if (!emailRegex.test(emailValue)) {
            emailError.textContent = 'Invalid email format';
            return false;
        } else {
            emailError.textContent = '';
            return true;
        }
    };

    const checkPasswordStrength = (password) => {
        const strengthIndicators = [
            { regex: /^.{8,}$/, level: 'weak', color: '#ff4d4d' },
            { regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{10,}$/, level: 'strong', color: '#2ecc71' },
            { regex: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&]).{12,}$/, level: 'very strong', color: '#3498db' }
        ];

        const result = strengthIndicators.findLast(indicator => indicator.regex.test(password));
        return result || { level: 'very weak', color: '#ff4d4d' };
    };

    const updatePasswordStrength = () => {
        const password = passwordInput.value;
        const strength = checkPasswordStrength(password);

        passwordStrengthBar.style.backgroundColor = strength.color;
        passwordStrengthBar.style.width = `${['very weak', 'weak', 'strong', 'very strong'].indexOf(strength.level) * 25}%`;
        passwordStrengthText.textContent = `Password strength: ${strength.level}`;
    };

    const validatePassword = () => {
        const passwordError = document.getElementById('password-error');
        const confirmPasswordError = document.getElementById('confirm-password-error');
        const passwordValue = passwordInput.value;
        const confirmPasswordValue = confirmPasswordInput.value;

        let isValid = true;

        if (!passwordValue) {
            passwordError.textContent = 'Password is required';
            isValid = false;
        } else if (passwordValue.length < 8) {
            passwordError.textContent = 'Password must be at least 8 characters';
            isValid = false;
        } else {
            passwordError.textContent = '';
        }

        if (confirmPasswordValue !== passwordValue) {
            confirmPasswordError.textContent = 'Passwords do not match';
            isValid = false;
        } else {
            confirmPasswordError.textContent = '';
        }

        return isValid;
    };

    const validateRole = () => {
        const roleError = document.getElementById('role-error');
        const selectedRole = document.querySelector('input[name="role"]:checked');

        if (!selectedRole) {
            roleError.textContent = 'Please select an account type';
            return false;
        } else {
            roleError.textContent = '';
            return true;
        }
    };

    const validateForm = () => {
        const isNameValid = validateName();
        const isEmailValid = validateEmail();
        const isPasswordValid = validatePassword();
        const isRoleValid = validateRole();

        return isNameValid && isEmailValid && isPasswordValid && isRoleValid;
    };

    const toggleSubmitButton = () => {
        const allInputsFilled = nameInput.value.trim() &&
                                emailInput.value.trim() &&
                                passwordInput.value &&
                                confirmPasswordInput.value &&
                                document.querySelector('input[name="role"]:checked');
        
        submitButton.disabled = !allInputsFilled;
    };

    // Event Listeners
    nameInput.addEventListener('input', () => {
        validateName();
        toggleSubmitButton();
    });

    emailInput.addEventListener('input', () => {
        validateEmail();
        toggleSubmitButton();
    });

    passwordInput.addEventListener('input', () => {
        updatePasswordStrength();
        validatePassword();
        toggleSubmitButton();
    });

    confirmPasswordInput.addEventListener('input', () => {
        validatePassword();
        toggleSubmitButton();
    });

    document.querySelectorAll('input[name="role"]').forEach(radio => {
        radio.addEventListener('change', () => {
            validateRole();
            toggleSubmitButton();
        });
    });

    // Password visibility toggle
    window.togglePasswordVisibility = (inputId) => {
        const passwordInput = document.getElementById(inputId);
        const toggleIcon = document.getElementById(`${inputId}-toggle-icon`);

        if (passwordInput.type === 'password') {
            passwordInput.type = 'text';
            toggleIcon.classList.remove('fa-eye-slash');
            toggleIcon.classList.add('fa-eye');
        } else {
            passwordInput.type = 'password';
            toggleIcon.classList.remove('fa-eye');
            toggleIcon.classList.add('fa-eye-slash');
        }
    };

    // Form submission
    signupForm.addEventListener('submit', async (event) => {
        event.preventDefault();

        if (validateForm()) {
            try {
                const formData = new FormData(signupForm);
                const response = await fetch('${pageContext.request.contextPath}/signup', {
                    method: 'POST',
                    body: formData
                });

                if (response.ok) {
                    // Redirect or show success message
                    window.location.href = '${pageContext.request.contextPath}/login?signup=success';
                } else {
                    // Handle signup errors
                    const errorData = await response.json();
                    alert(errorData.message || 'Signup failed. Please try again.');
                }
            } catch (error) {
                console.error('Signup error:', error);
                alert('An error occurred. Please try again.');
            }
        }
    });
});