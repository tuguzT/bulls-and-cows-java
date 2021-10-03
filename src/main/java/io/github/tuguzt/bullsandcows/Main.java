package io.github.tuguzt.bullsandcows;

/**
 * Main class with entry point.
 */
public class Main {
    /**
     * Entry point of the game.
     *
     * @param args arguments of the game
     */
    public static void main(String[] args) {
        var application = new Application(args);
        application.run();
    }
}
