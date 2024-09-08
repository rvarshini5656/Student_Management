import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

class Question {
    String questionText;
    String[] options;
    char correctAnswer;

    public Question(String questionText, String[] options, char correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void displayQuestion() {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            System.out.println((char) ('A' + i) + ": " + options[i]);
        }
    }

    public boolean checkAnswer(char userAnswer) {
        return Character.toUpperCase(userAnswer) == correctAnswer;
    }
}

public class QuizGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Question> questions = new ArrayList<>();

        // Add some questions
        questions.add(new Question("Which programming language is known as the 'write once, run anywhere' language?",
                new String[]{"Python", "C++", "Java", "Ruby"}, 'C'));
        questions.add(new Question("Who developed the theory of relativity?",
                new String[]{"Isaac Newton", "Albert Einstein", "Galileo Galilei", "Marie Curie"}, 'B'));
        questions.add(new Question("What is the capital of France?",
                new String[]{"Berlin", "Paris", "Rome", "Madrid"}, 'B'));
        questions.add(new Question("Which planet is known as the Red Planet?",
                new String[]{"Earth", "Jupiter", "Mars", "Saturn"}, 'C'));
        questions.add(new Question("What is the chemical symbol for water?",
                new String[]{"HO", "H2O", "O2", "OH"}, 'B'));

        // Shuffle the questions to randomize the order
        Collections.shuffle(questions);

        int score = 0;

        // Ask each question
        for (Question q : questions) {
            q.displayQuestion();
            System.out.print("Enter your answer (A/B/C/D): ");
            char userAnswer = scanner.next().charAt(0);

            if (q.checkAnswer(userAnswer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong answer! The correct answer was: " + q.correctAnswer + "\n");
            }
        }

        // Display final score
        System.out.println("Quiz completed! Your final score is: " + score + "/" + questions.size());

        scanner.close();
    }
}