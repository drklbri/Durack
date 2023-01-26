package ru.vsu.cs.shevchenko_daniil.game;

public class BotPlayer extends AbstractPlayer {

    public BotPlayer(String name) {
        super(name);
    }


    @Override
    public Card defend(Card attackCard, Table table) {
      return null;
    }

    public void getCards(Table table) {
        while (hand.size() < 6)
            hand.add(table.getDeck().remove(0));
    }

    @Override
    public Card attack(Table table) {
        return null;
    }

}
