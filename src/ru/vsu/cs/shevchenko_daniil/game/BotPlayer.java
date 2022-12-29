package ru.vsu.cs.shevchenko_daniil.game;


import ru.vsu.cs.shevchenko_daniil.enums.PlayerState;
import ru.vsu.cs.shevchenko_daniil.enums.PlayerStatus;
import ru.vsu.cs.shevchenko_daniil.enums.Suit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BotPlayer implements Player {
    private int id;
    private String name;
    private List<Card> hand = new ArrayList<>();
    private PlayerStatus status;
    private PlayerState state;
    private int count = 0;

    public BotPlayer(String name, Table table, int id) {
        this.name = name;
        while (hand.size() < 6)
            hand.add(table.getDeck().remove(0));
        this.status = PlayerStatus.PLAYING;
        this.state = PlayerState.NONE;
        this.id = id;
    }


    @Override
    public boolean isBot() {
        return true;
    }

    @Override
    public void takeCardFromTable(Table table) {

        hand.addAll(table.getCardsOnTable());
        table.getCardsOnTable().clear();
    }

    @Override
    public void takeCard(Table table) {
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
    public void setId(int id) {
        this.id = id;
    }


    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    public void setState(PlayerState state) {
        this.state = state;
    }

    @Override
    public PlayerState getState() {
        return state;
    }

    @Override
    public PlayerStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }

    @Override
    public int getId() {
        return id;
    }

    public boolean defend(Card attackCard, Table table) {
        Card bestCard = null;
        int index = 0;
        int value = 0;

        for (Card card : hand) {
            if (attackCard.getSuit().equals(card.getSuit()) && attackCard.getPower() < card.getPower()) {
                if (bestCard == null || bestCard.getPower() > card.getPower()) {
                    bestCard = card;
                    index = value;
                }
            }
            value++;
        }

        if (bestCard == null && !attackCard.getSuit().equals(table.getTrumpCard().getSuit())) {
            value = 0;
            for (Card card : hand) {
                if (card.getSuit().equals(table.getTrumpCard().getSuit())) {
                    if (bestCard == null || bestCard.getPower() > card.getPower()) {
                        bestCard = card;
                        index = value;

                    }
                }
                value++;
            }
        }
        if (bestCard == null) {
            return false;
        } else {
            table.getCardsOnTable().add(hand.remove(index));
        }
        return true;
    }

    @Override
    public Card attack(Table table) {
        Card card = null;
        Suit color = table.getTrumpCard().getSuit();
        if (hand.size() == 0) {
            this.status = PlayerStatus.WON;
            System.out.println(name + " Выиграл!");
            return null;
        }
        if(table.getCardsOnTable().size() == 0){
            card = hand.get(0);
            for (Card card1 : hand) {
                if(card.getPower() > card1.getPower()){
                    if(!card1.getSuit().equals(color)){
                        card = card1;
                    }else if(card.getSuit().equals(color)){
                        card = card1;
                    }
                }else if(card.getSuit().equals(color) && !card1.getSuit().equals(color)){
                    card = card1;
                }
            }
        }else{
                boolean temp = false;
                for (Card cardTable : table.getCardsOnTable()) {
                    for (Card cardBot : hand) {
                        if (cardBot.getPower() == cardTable.getPower()) {
                            card = cardBot;
                            temp = true;
                            break;
                        }
                    }
                    if (temp) {
                        break;
                    }
                }
            }
            if (card != null) {
                table.getCardsOnTable().add(card);
                hand.remove(card);
            }
            return card;
        }

}
