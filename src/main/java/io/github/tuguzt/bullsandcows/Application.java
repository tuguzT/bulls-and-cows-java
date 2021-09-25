package io.github.tuguzt.bullsandcows;

import java.util.*;

public class Application {
    private final List<String> commands;

    private static final String RULES = """
            Let me tell you the rules:
            \t- computer generates a 4-digit positive number (all the digits are different),
            \t- and YOU, the player, must guess it!
            If the matching digits are in their right positions, they are called 'bulls', but if in different positions, they are 'cows'.
            """;

    private static final String HELP = """
            'Bulls and Cows' game
            Usage: bulls_and_cows <options>
            Available options:
            \t--play <attempts> : play the game with given count of attempts (default is 4)
            \t--help            : shows all available options
            \t--rules           : shows the rules of the game
            """;

    /**
     * Constructs an application instance.
     *
     * @param args commands from the console
     */
    public Application(String[] args) {
        commands = Arrays.asList(args);
    }

    /**
     * Executes main logic of the application.
     */
    public void run() {
        if (commands.isEmpty() || Objects.equals(commands.get(0), "--help")) {
            System.out.println(HELP);
            return;
        }
        if (Objects.equals(commands.get(0), "--rules")) {
            System.out.println(RULES);
            return;
        }
        if (Objects.equals(commands.get(0), "--play")) {
            var attemptsCount = 4;
            if (commands.size() == 2) {
                try {
                    final var attempts = Integer.parseInt(commands.get(1));
                    if (attempts < 4) {
                        System.out.println("Count of attempts mustn't be less than 4!");
                        return;
                    }
                    attemptsCount = attempts;
                } catch (NumberFormatException e) {
                    System.out.println("Count of attempts must be positive number!");
                }
            }
            play(attemptsCount);
            return;
        }
        System.out.println("Wrong option was given! Use --help option to see available options.");
    }

    void play(int attemptsCount) {
        System.out.println("""
                Hello there!
                You're playing in the 'Bulls and Cows' game!
                """ + RULES +
                "One important note: you have only " +
                attemptsCount +
                " attempts to guess the number!");
        final var secretNumber = Utils.generateSecretNumber();
        final var scanner = new Scanner(System.in);
        while (attemptsCount > 0) {
            System.out.println("\n" + attemptsCount + " attempts remaining. Enter your number: ");
            try {
                var userNumber = scanner.nextInt();
                if (userNumber < 1_000) {
                    System.out.println("Your number is too small! It must be 4-digit!");
                    continue;
                }
                if (userNumber > 9_999) {
                    System.out.println("Your number is too big! It must be 4-digit!");
                    continue;
                }
                final var cows = Utils.countCows(secretNumber, userNumber);
                final var bulls = Utils.countBulls(secretNumber, userNumber);
                if (bulls == 4) {
                    System.out.println("CONGRATULATIONS!!! You have guessed the secret number!");
                    return;
                }
                System.out.println(cows + " cows and " + bulls + " bulls for now!");
                attemptsCount--;
            } catch (InputMismatchException e) {
                System.out.println("Your input is not a positive 4-digit number!");
                scanner.next();
            }
        }
        System.out.println("\nSorry, but you have lost: you haven't guess a secret number!");
    }
}
