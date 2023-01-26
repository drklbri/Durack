package ru.vsu.cs.shevchenko_daniil.game;

import ru.vsu.cs.shevchenko_daniil.enums.Suit;

import java.util.LinkedList;

public interface Player {

    void takeCardFromDeck(Table table);

    void takeCardFromTable(Table table);

    String getName();

    Card attack(Table table);

    Card defend(Card attackCard, Table table);

    LinkedList<Card> getHand();

    void getCards(Table table);

    void sortDeck(Suit suit);

}
