document.addEventListener('DOMContentLoaded', (event) => {
    console.log('DOM fully loaded and parsed');

    // Example function to calculate exam score
    function calculateScore(answers, correctAnswers) {
        let score = 0;
        for (let i = 0; i < answers.length; i++) {
            if (answers[i] === correctAnswers[i]) {
                score++;
            }
        }
        return score;
    }

    // Example usage
    const userAnswers = ['A', 'B', 'C', 'D'];
    const correctAnswers = ['A', 'B', 'C', 'D'];
    const score = calculateScore(userAnswers, correctAnswers);

    console.log('User score:', score);
});