package ru.vsu.cs.shevchenko_daniil.game;

import ru.vsu.cs.shevchenko_daniil.enums.Rank;
import ru.vsu.cs.shevchenko_daniil.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Table {
    private List<Card> deck = new ArrayList<>();
    private List<Card> cardsOnTable = new ArrayList<>();
    private Card trumpCard;

    public Table() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values())
                deck.add(new Card(rank, suit));
        }
        searchTrumpCard();
        Collections.shuffle(deck);
    }

    public List<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    public List<Card> getDeck() {
        return deck;
    }

    private void searchTrumpCard() {
        trumpCard = deck.get((int) (Math.random() * 36));
    }


    public Card getTrumpCard() {
        return trumpCard;
    }


}
