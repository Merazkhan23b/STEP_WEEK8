
import java.util.*;

abstract class Question {
    String questionText, correctAnswer, studentAnswer;
    double points;

    Question(String questionText, String correctAnswer,
             String studentAnswer, double points) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    abstract double evaluate();
}

class MCQ extends Question {
    MCQ(String q, String c, String s, double p) {
        super(q, c, s, p);
    }

    double evaluate() {
        return studentAnswer.equalsIgnoreCase(correctAnswer)
                ? points : 0.0;
    }
}

class TF extends Question {
    TF(String q, String c, String s, double p) {
        super(q, c, s, p);
    }

    double evaluate() {
        return studentAnswer.equalsIgnoreCase(correctAnswer)
                ? points : 0.0;
    }
}

class ESSAY extends Question {
    ESSAY(String q, String c, String s, double p) {
        super(q, c, s, p);
    }

    double evaluate() {
        String[] keywords = correctAnswer.split(",");
        String answer = studentAnswer.toLowerCase();
        int count = 0;

        for (String keyword : keywords) {
            String k = keyword.trim().toLowerCase();

            if (!k.isEmpty() &&
                java.util.regex.Pattern.compile(
                    "\\b" + java.util.regex.Pattern.quote(k) + "\\b")
                    .matcher(answer).find()) {
                count++;
            }
        }

        if (count >= 2)
            return points * 0.75;
        else if (count == 1)
            return points * 0.50;
        else
            return 0.0;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine().trim());
        Question[] questions = new Question[n];
        double total = 0.0;

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();

            java.util.regex.Matcher m =
                java.util.regex.Pattern.compile(
                    "^([A-Z]+)\\s+\"([^\"]*)\"\\s+\"([^\"]*)\"\\s+\"([^\"]*)\"\\s+(\\d+(?:\\.\\d+)?)$")
                .matcher(line);

            if (!m.matches()) {
                throw new IllegalArgumentException(
                    "Invalid input format: " + line);
            }

            String type = m.group(1);
            String q = m.group(2);
            String correct = m.group(3);
            String student = m.group(4);
            double points = Double.parseDouble(m.group(5));

            switch (type) {
                case "MCQ":
                    questions[i] = new MCQ(q, correct, student, points);
                    break;
                case "TF":
                    questions[i] = new TF(q, correct, student, points);
                    break;
                case "ESSAY":
                    questions[i] = new ESSAY(q, correct, student, points);
                    break;
                default:
                    throw new IllegalArgumentException(
                        "Unknown question type: " + type);
            }
        }

        for (Question question : questions) {
            double score = question.evaluate();

            System.out.printf("%s: %.2f%n",
                question.getClass().getSimpleName(), score);

            total += score;
        }

        System.out.printf("Total Score: %.2f%n", total);

        sc.close();
    }
}
