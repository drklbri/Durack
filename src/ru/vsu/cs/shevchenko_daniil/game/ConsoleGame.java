package ru.vsu.cs.shevchenko_daniil.game;

import java.util.*;

public class ConsoleGame {
    Scanner scanner = new Scanner(System.in);

    private final Table table = new Table();
    Player attacker;
    Player defender;

    public void start() {
        table.setTrumpCard();
        SingleLinkedList<Player> players = createPlayers();
        initFirstMove(players);

        while (gameEnd(players)) {
            circle(attacker, defender);
            updatePlayers(players);
        }

        System.out.println("Конец игры. Победил игрок " + players.getFirst().getName());

    }

    private void initFirstMove(SingleLinkedList<Player> players) {
        HashMap<String, Card> map = new HashMap();
        CardComparator comparator = new CardComparator();
        for (int i = 0; i < players.getSize(); i++) {
            map.put(players.get(i).getName(), players.get(i).getHand().getLast());
        }

        Player tempPlayer = players.getFirst();
        Card tempCard = players.getFirst().getHand().getLast();
        for (int i = 0; i < players.getSize(); i ++) {
            if (comparator.compareBySuit(tempCard, map.get(Integer.toString(i)), table.getTrumpCard()) < 0)
                tempPlayer = players.get(i);
                tempCard = map.get(Integer.toString(i));
        }

        attacker = players.get(Integer.parseInt(tempPlayer.getName()));
        defender = players.getNext(tempPlayer);

    }

    private void updatePlayers(SingleLinkedList<Player> players) {
        for (int i = 0; i < players.getSize(); i++) {
            if (players.get(i).getHand().size() == 0)
                players.remove(players.get(i));
        }
    }

    private void transfer(SingleLinkedList<Player> players) {
        if (players.getSize() == 2) {
            players.swap();
        } else {
            defender = players.getNext(defender);
            attacker = players.getNext(attacker);
        }
    }

    private boolean gameEnd(SingleLinkedList<Player> players) {
        return players.getSize() > 1;
    }

    private void circle(Player attacker, Player defender) {

    }

    public SingleLinkedList<Player> createPlayers() {
        System.out.println("Введите количество игроков не больше 4");
        int count = scanner.nextInt();
        SingleLinkedList<Player> playersQueue = new SingleLinkedList<>();
        for (int i = 0; i < count; i++) {
            playersQueue.add(new BotPlayer(Integer.toString(i)));
            playersQueue.getLast().getCards(table);
            playersQueue.getLast().sortDeck(table.getTrumpCard());
        }
        return playersQueue;
    }

}
