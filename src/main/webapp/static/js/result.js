document.addEventListener('DOMContentLoaded', function() {
    const resultElement = document.getElementById('result');

    function displayResult(message) {
        resultElement.textContent = message;
    }

    // Example usage
    displayResult('Hello, World!');
});