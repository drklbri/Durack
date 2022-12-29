package ru.vsu.cs.shevchenko_daniil;

import ru.vsu.cs.shevchenko_daniil.game.BotPlayer;
import ru.vsu.cs.shevchenko_daniil.game.Game;
import ru.vsu.cs.shevchenko_daniil.game.HumanPlayer;
import ru.vsu.cs.shevchenko_daniil.game.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }
}
