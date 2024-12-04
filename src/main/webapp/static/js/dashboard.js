document.addEventListener('DOMContentLoaded', function() {
    // Set greeting based on time
    function setGreeting() {
        const greetingTime = document.getElementById('greeting-time');
        const hour = new Date().getHours();
        
        if (hour < 12) {
            greetingTime.textContent = 'Good morning';
        } else if (hour < 18) {
            greetingTime.textContent = 'Good afternoon';
        } else {
            greetingTime.textContent = 'Good evening';
        }
    }
    setGreeting();

    // Navigation active state
    const navItems = document.querySelectorAll('.nav-item');
    navItems.forEach(item => {
        item.addEventListener('click', function() {
            navItems.forEach(i => i.classList.remove('active'));
            this.classList.add('active');
        });
    });

    // Logout function
    function logout() {
        if (confirm('Are you sure you want to log out?')) {
            // Simulate logout (actual implementation will vary)
            window.location.href = `${window.location.origin}${window.contextPath}/login`;
        }
    }

    // Export logout function to global scope for onclick usage
    window.logout = logout;

    // Mock functions for educator and student actions
    function openCreateExamModal() {
        alert('Create Exam Modal opened (implement modal logic here)');
    }

    function openAvailableExamsModal() {
        alert('Available Exams Modal opened (implement modal logic here)');
    }

    // Export educator and student actions to global scope
    window.openCreateExamModal = openCreateExamModal;
    window.openAvailableExamsModal = openAvailableExamsModal;

    // Search bar functionality
    const searchInput = document.getElementById('dashboard-search');
    searchInput.addEventListener('input', function() {
        const query = this.value.trim();
        if (query.length > 0) {
            console.log(`Searching for: ${query}`);
            // Implement search functionality here
        }
    });

    // Example for rendering a chart (for performance overview)
    const performanceChartElement = document.getElementById('student-performance-chart');
    if (performanceChartElement) {
        const ctx = performanceChartElement.getContext('2d');
        new Chart(ctx, {
            type: 'bar',
            data: {
                labels: ['Math', 'Science', 'History', 'English', 'Arts'],
                datasets: [{
                    label: 'Scores',
                    data: [85, 78, 92, 74, 88],
                    backgroundColor: [
                        'rgba(74, 144, 226, 0.8)',
                        'rgba(74, 144, 226, 0.8)',
                        'rgba(74, 144, 226, 0.8)',
                        'rgba(74, 144, 226, 0.8)',
                        'rgba(74, 144, 226, 0.8)'
                    ],
                    borderColor: 'rgba(74, 144, 226, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    y: {
                        beginAtZero: true
                    }
                }
            }
        });
    }
});
