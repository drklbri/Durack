package ru.vsu.cs.shevchenko_daniil.game;

import com.sun.source.tree.PatternTree;
import ru.vsu.cs.shevchenko_daniil.enums.PlayerState;
import ru.vsu.cs.shevchenko_daniil.enums.PlayerStatus;

import java.util.*;

public class Game {
    Scanner scanner = new Scanner(System.in);

    private Table table = new Table();
    Player attacker;
    Player defender;

    public void start() {
        List<Player> players = createPlayers();
        initFirstMove(players);

        while (gameEnd(players)) {
            System.out.println();
            System.out.println("Игрок '" + attacker.getName() + "' атакует игрока '" + defender.getName() + "'");
            circle(attacker, defender, players);
            updatePlayers(players);
        }

    }

    private void initFirstMove(List<Player> players) {
        if (firstMove(players) != null) {
            attacker = firstMove(players);
            defender = players.get((attacker.getId() + 1) % players.size());
            System.out.println("Первым ходит игрок под ником " + players.get(attacker.getId()).getName());
        } else {
            attacker = players.get(0);
            defender = players.get(1);
            defender.setState(PlayerState.DEFEND);
            System.out.println("Первым ходит игрок под ником " + players.get(attacker.getId()).getName());
        }
    }

    private void updatePlayers(List<Player> players) {
        players.removeIf(player -> player.getStatus() == PlayerStatus.WON);
        if (players.size() > 1) {
            if (defender.getId() + 1 == players.size() && attacker.getId() + 1 == players.size() - 1) {
                attacker = players.get(players.size() - 1);
                defender = players.get(0);
            } else if (attacker.getId() + 1 == players.size() && defender.getId() == 0) {
                attacker = players.get(0);
                defender = players.get(1);
            } else {
                final Player temp = defender;
                defender = players.get((players.indexOf(defender) + 1) % players.size());
                attacker = players.get((players.indexOf(temp)) % players.size());
            }
        }
    }

    private boolean gameEnd(List<Player> players) {
        if (players.size() != 1)
            return true;
        else {
            System.out.println("Игрок " + players.get(0).getName() + " проиграл!");
            return false;
        }

    }

    private void circle(Player attacker, Player defender, List<Player> players) {
        while (true) {

            if (!attacker.isBot())
                PrintStuff.printMove(table, attacker);

            Card attackCard = attacker.attack(table);
            if (attackCard != null) {

                if (attacker.isBot()) {
                    System.out.print("Бот атаковал: ");
                    PrintStuff.printTable(table.getCardsOnTable());

                }

                if (!defender.isBot())
                    PrintStuff.printMove(table, defender);

                if (defender.defend(attackCard, table)) {
                    if (defender.isBot())

                        System.out.print("Бот защитился: ");
                    PrintStuff.printTable(table.getCardsOnTable());

                    continue;
                } else {

                    System.out.println("Игрок " + defender.getName() + " не отбился");
                    PrintStuff.printTable(table.getCardsOnTable());

                    defender.takeCardFromTable(table);
                    attacker.takeCard(table);
                    table.getCardsOnTable().clear();
                    break;
                }
            } else {

                System.out.println("Игрок " + defender.getName() + " отбился");
                System.out.print("Стол на момент отбития: ");
                PrintStuff.printTable(table.getCardsOnTable());

                if (attacker.getStatus() == PlayerStatus.WON)
                    break;

                if (table.getCardsOnTable().size() != 0) {
                    attacker.takeCardFromTable(table);
                    table.getCardsOnTable().clear();
                }
                else
                    attacker.takeCard(table);
                defender.takeCard(table);
                table.getCardsOnTable().clear();
                break;
            }
        }
    }


    public Player firstMove(List<Player> players) {
        Card[][] lowestTrumpCard = new Card[players.size()][6];
        int lTC = 14;
        int player = -1;
        for (int i = 0; i < lowestTrumpCard.length; i++) {
            for (int j = 0; j < 6; j++) {
                lowestTrumpCard[i][j] = players.get(i).getHand().get(j);
            }
        }

        for (int i = 0; i < lowestTrumpCard.length; i++) {
            for (int j = 0; j < 6; j++) {
                if (lowestTrumpCard[i][j].sameSuit(table.getTrumpCard()) && lowestTrumpCard[i][j].getPower() < lTC) {
                    lTC = lowestTrumpCard[i][j].getPower();
                    player = i;
                }
            }
        }
        if (player == -1) {
            System.out.println("Козыря нет ни у одного из игроков");
            return null;
        } else {
            return players.get(player);
        }

    }

    public List<Player> createPlayers() {
        List<Player> players = new ArrayList<>();
        System.out.println("Введите число игроков от 2 до 6");
        int playerCount = scanner.nextInt();
        if (playerCount < 2)
            playerCount = 2;
        if (playerCount > 6)
            playerCount = 6;

        System.out.println("Введите число ботов");
        int botCount = scanner.nextInt();
        if (botCount > 6)
            botCount = 6;
        for (int i = 0; i < playerCount - botCount; i++) {
            System.out.println("Введите имя игрока");
            players.add(new HumanPlayer(scanner.next(), table, i));
        }
        if (botCount > 0) {
            for (int i = 0; i < botCount; i++) {
                System.out.println("Введите имя бота");
                players.add(new BotPlayer(scanner.next(), table, playerCount - botCount + i));
            }
        }
        return players;
    }

}
