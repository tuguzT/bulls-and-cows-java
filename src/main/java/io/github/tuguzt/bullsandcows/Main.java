package io.github.tuguzt.bullsandcows;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class with entry point.
 */
public class Main {
    public static List<Double> list = new ArrayList<>();

    public void populateList() {
        for (int i = 0; i < 10000000; i++) {
            list.add(Math.random());
        }
    }

    /**
     * Entry point of the game.
     *
     * @param args arguments of the game
     */
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++)
            new Main().populateList();

        var application = new Application(args);
        application.run();
    }
}
