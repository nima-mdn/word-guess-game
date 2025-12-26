import java.util.Scanner;
import java.util.Random;
public class Main {
    static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String[] words = {
                "دانشگاه",
                "برنامه",
                "کامپیوتر",
                "کلید",
                "جاوا"
        };

        String secretWord = words[random.nextInt(words.length)];

        char[] guessedWord = new char[secretWord.length()];
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        int attempts = 6;
        int score = 100;
        boolean gameWon = false;
        boolean hintUsed = false;

        System.out.println("بازی حدس کلمه شروع شد!");
        System.out.println("تعداد حروف کلمه: " + secretWord.length());

        while (attempts > 0 && !gameWon) {

            // نمایش وضعیت کلمه
            System.out.print("وضعیت کلمه: ");
            for (char c : guessedWord) {
                System.out.print(c + " ");
            }
            System.out.println();

            // شمارش حروف باز شده
            int revealedLetters = 0;
            for (char c : guessedWord) {
                if (c != '_') revealedLetters++;
            }

            if (!hintUsed) {
                System.out.println("اگر می‌خوای Hint بگیری، 'hint' وارد کن");
            }

            if (revealedLetters >= 2) {
                System.out.println("می‌تونی کل کلمه رو حدس بزنی یا یک حرف وارد کن:");
            } else {
                System.out.println("یک حرف وارد کن:");
            }

            String input = scanner.nextLine().trim();

            // 🔴 جلوگیری از کرش
            if (input.length() == 0) {
                System.out.println("ورودی نامعتبر!");
                continue;
            }

            // Hint
            if (input.equalsIgnoreCase("hint") && !hintUsed) {
                hintUsed = true;
                score -= 10;

                int index;
                do {
                    index = random.nextInt(secretWord.length());
                } while (guessedWord[index] != '_');

                guessedWord[index] = secretWord.charAt(index);
                System.out.println("Hint فعال شد! یک حرف نشان داده شد.");
                continue;
            }

            // حدس کل کلمه
            if (input.length() > 1) {
                if (input.equals(secretWord)) {
                    gameWon = true;
                    score += 20;
                    break;
                } else {
                    attempts--;
                    score -= 10;
                    System.out.println("کلمه اشتباه بود!");
                    System.out.println("تلاش باقی‌مانده: " + attempts);
                    continue;
                }
            }

            // حدس یک حرف
            char guessChar = input.charAt(0);
            boolean correctGuess = false;

            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guessChar && guessedWord[i] == '_') {
                    guessedWord[i] = guessChar;
                    correctGuess = true;
                }
            }

            if (correctGuess) {
                System.out.println("حرف درست بود!");
            } else {
                attempts--;
                score -= 10;
                System.out.println("حرف اشتباه!");
                System.out.println("تلاش باقی‌مانده: " + attempts);
            }

            // بررسی برد
            gameWon = true;
            for (char c : guessedWord) {
                if (c == '_') {
                    gameWon = false;
                    break;
                }
            }
        }

        // پایان بازی
        if (gameWon) {
            System.out.println("\nتبریک! کلمه کامل شد:");
            System.out.println(secretWord);
            System.out.println("امتیاز نهایی: " + score);
        } else {
            System.out.println("\nباختی!");
            System.out.println("کلمه درست: " + secretWord);
        }

        scanner.close();
    }
}
