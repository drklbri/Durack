package ru.vsu.cs.shevchenko_daniil.game;

import ru.vsu.cs.shevchenko_daniil.enums.Rank;
import ru.vsu.cs.shevchenko_daniil.enums.Suit;

import java.util.Objects;

public class Card {
   private final Rank rank;
   private final Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public String toString() {
        return rank.getName() + suit.getSuit();
    }

    public Suit getSuit() {
        return suit;
    }

    public Rank getRank() {
        return rank;
    }

    public int getPower() {
        return rank.getRank();
    }

    public int compareTo(Card otherCard) {
        return this.getPower() - otherCard.getPower();
    }

    private boolean isTrump(Table table) {
        return suit.equals(table.getTrumpCard().getSuit());
    }

    public boolean sameSuit(Card card){
        return suit.equals(card.getSuit());
    }

    public int trueCompareTo(Card otherCard, Table table) {
        int valueDifference = this.compareTo(otherCard);
        boolean thisTrump = this.isTrump(table);
        boolean otherTrump = otherCard.isTrump(table);
        if (thisTrump && otherTrump) {
            return valueDifference;
        } else if (thisTrump && !otherTrump) {
            return 1;
        } else if (!thisTrump && otherTrump) {
            return -1;
        } else if (sameSuit(otherCard)) {
            return valueDifference;
        } else {
            return -1;
        }
    }
}
