package ru.vsu.cs.shevchenko_daniil.enums;

public enum Suit {
    CLOVERS("♣"),
    HEARTS("♥"),
    PIKES("♠"),
    TILES("♦");

    private final String suit;

    Suit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
