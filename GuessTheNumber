import java.io.*;
import java.util.*;

public class guessing {
    private Scanner input;

    public guessing(Scanner input) {
        this.input = input;
    }

    public void start() {
        // make a guessing game that has the user pick a variable between 1 and 10 then
        // makes the player guess the number
        int number = 0;
        int guess = 0;
        int tries = 0;
        int maxtries = 3;
        int min = 1;
        int max = 10;
        String response;
        do {
            System.out.println("Guess a number between 1 and 10.");
            System.out.println("You have 3 tries to guess the number.");
            int range = max - min + 1;
            number = (int) (Math.random() * range) + min;
            do {
                System.out.println("Enter your guess: ");
                guess = input.nextInt();
                tries++;
                if (number == guess) {
                    System.out.println("You guessed the number!");
                    // exit the program
                    return;
                } else {
                    System.out.println("You did not guess the number. try again.");
                }
                if (guess < number) {
                    System.out.println("Your guess is too low.");
                    System.out.println("you have " + (maxtries - tries) + " tries left.");
                } else if (guess > number) {
                    System.out.println("Your guess is too high.");
                    System.out.println("you have " + (maxtries - tries) + " tries left.");
                }
            } while (guess != number && tries < maxtries);
            System.out.println("You did not guess the number. The number was " + number);
            System.out.println("Game Over.");
            System.out.println("would you like to try again with the computer picking a new number? (y/n)");
            response = input.next();
        } while (response.equals("y"));
        System.out.println("Thanks for playing!");
    }

    public static void main(String[] args) {
        guessing game = new guessing(new Scanner(System.in));
        game.start();
    }
}
