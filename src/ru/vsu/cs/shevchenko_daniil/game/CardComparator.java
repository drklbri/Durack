package ru.vsu.cs.shevchenko_daniil.game;

import ru.vsu.cs.shevchenko_daniil.enums.Suit;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        if (o1.getRank().getRank() >= o2.getRank().getRank())
            return 0;
        else return -1;
    }

    public int compareBySuit(Card o1, Card o2, Suit suit) {
        final Comparator<Card> cardComparator = new CardComparator();
        if (o1.getSuit().equals(o2.getSuit())){
            return cardComparator.compare(o1, o2);
        } else if (o1.getSuit().equals(suit)) {
            return 0;
        } else if (!o1.getSuit().equals(suit) && o2.getSuit().equals(suit))
            return 0;
        return -1;
    }

}
