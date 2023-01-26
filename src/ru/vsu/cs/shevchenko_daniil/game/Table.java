package ru.vsu.cs.shevchenko_daniil.game;

import ru.vsu.cs.shevchenko_daniil.enums.Rank;
import ru.vsu.cs.shevchenko_daniil.enums.Suit;

import java.util.*;

public class Table {
    private ArrayList<Card> deck = new ArrayList<>();
    private Deque<Card> cardsOnTable = new ArrayDeque<>();
    private Card trumpCard;

    public Table() {
        for (Rank rank : Rank.values()) {
            for (Suit suit : Suit.values())
                deck.add(new Card(rank, suit));
        }
        Collections.shuffle(deck);
    }

    public void setTrumpCard() {
        trumpCard = deck.get(0);
        deck.remove(trumpCard);
        deck.add(trumpCard);
    }

    public Deque<Card> getCardsOnTable() {
        return cardsOnTable;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }



    public Suit getTrumpCard() {
        return trumpCard.getSuit();
    }


}
