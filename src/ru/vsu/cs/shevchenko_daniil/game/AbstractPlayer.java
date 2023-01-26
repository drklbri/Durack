package ru.vsu.cs.shevchenko_daniil.game;

import ru.vsu.cs.shevchenko_daniil.enums.Suit;

import java.util.*;

public abstract class AbstractPlayer implements Player {
    protected String name;
    protected LinkedList<Card> hand = new LinkedList<>();

    public AbstractPlayer(String name) {
        this.name = name;
    }

    public void getCards(Table table) {
        while (hand.size() < 6)
            hand.add(table.getDeck().remove(0));
    }

    private void swap(LinkedList<Card> hand, int card1, int card2) {
        Card temp = hand.get(card1);
        hand.set(card1, hand.get(card2));
        hand.set(card2, temp);
    }


    public void sortDeck(Suit suit) {
        CardComparator cardComparator = new CardComparator();
        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                final Card card1 = hand.get(j);
                final Card card2 = hand.get(j-1);
                if (cardComparator.compareBySuit(card1, card2, suit) < 0) {
                    swap(hand, j, j-1);
                } else {
                    break;
                }
            }
        }
    }


    @Override
    public void takeCardFromTable(Table table) {

        hand.addAll(table.getCardsOnTable());
        table.getCardsOnTable().clear();
    }

    @Override
    public void takeCardFromDeck(Table table) {
        int needCards = 6 - hand.size();

        if (needCards == table.getDeck().size()) {
            for (int i = 0; i < needCards; i++) {
                hand.add(table.getDeck().remove(0));
            }
        } else if (needCards > table.getDeck().size()) {
            hand.addAll(table.getDeck());
            table.getDeck().clear();
        } else {
            for (int i = 0; i < needCards; i++) {
                hand.add(table.getDeck().remove(0));
            }
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LinkedList<Card> getHand() {
        return hand;
    }


    public abstract Card defend(Card attackCard, Table table);

    @Override
    public abstract Card attack(Table table);
}
