
import java.util.InputMismatchException;
import java.util.Scanner;

public class GuessGameConsole {

    public static void main(String[] args) {
        System.out.println("Hello To Guessing Game!");
        Scanner input = new Scanner(System.in);

        boolean playAgain = true;
        while (playAgain) {
            guess(input);
            System.out.println("Do you want to play again? (yes/no)");
            String response = input.next().toLowerCase();
            if (!response.equals("yes")) {
                playAgain = false;
                System.out.println("Thank you for playing!");
            }
        }

        input.close(); // Close the scanner when done
    }

    public static void guess(Scanner input) {
        int min = 1;
        int max = 10;
        int rand = min + (int) (Math.random() * ((max - min) + 1));
        int randomInt = min + (int) (Math.random() * ((max - min) + 1));
        System.out.println("Enter Your Guessings!");
        System.out.printf("You have %d trials to guess the right number between %d and %d.%n", randomInt, min, max);

        boolean guessedCorrectly = false;

        // input from user
        for (int i = 0; i < randomInt; i++) {
            int number = -1; // Default invalid value
            boolean validInput = false;

            // Ensure valid integer input
            while (!validInput) {
                System.out.printf("Trial %d: ", i + 1);
                try {
                    number = input.nextInt();
                    validInput = true; // Input is valid, exit loop
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    input.next(); // Clear the invalid input
                }
            }

            if (number == rand) {
                System.out.println("Congrats! You guessed right.");
                guessedCorrectly = true;
                break;
            } else if (number > rand) {
                System.out.println("Too high!");
            } else {
                System.out.println("Too low!");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("You've reached your limit of trials. The correct number was " + rand + ".");
        }
    }
}
