package ru.vsu.cs.shevchenko_daniil.game;

import ru.vsu.cs.shevchenko_daniil.enums.PlayerState;
import ru.vsu.cs.shevchenko_daniil.enums.PlayerStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanPlayer implements Player {
    private int id;
    private String name;
    private PlayerState state;
    private PlayerStatus status;
    private List<Card> hand = new ArrayList<>();
    private int count = 0;

    public HumanPlayer(String name, Table table, int id) {
        this.name = name;

        while (hand.size() < 6)
            hand.add(table.getDeck().remove(0));

        this.state = PlayerState.NONE;
        this.status = PlayerStatus.PLAYING;
        this.id = id;
    }




    @Override
    public PlayerState getState() {
        return state;
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

    public void setState(PlayerState state) {
        this.state = state;
    }

    public void setStatus(PlayerStatus status) {
        this.status = status;
    }

    @Override
    public boolean isBot() {
        return false;
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
    public int getId() {
        return id;
    }

    public int numberCard(int size, boolean b){
        int select = 0;
        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt() ){
            select = scanner.nextInt();
            if(select == 0 &&  b){
                return select;
            }
            if(select > size || select <1){
                System.out.println("Некорректный ввод");
                return numberCard(size, b);
            }
        }else{
            System.out.println("Некорректный ввод");
            return numberCard(size, b);
        }
        return select;
    }

    public int correctNumber(Table table) {
        int correctNum = numberCard(hand.size(), true);
        boolean b = false;
        while (true) {
            if (correctNum == 0) {
                return 0;
            }

            for (Card card : table.getCardsOnTable()) {
                if (hand.get(correctNum - 1).getRank().equals(card.getRank())) {
                    b = true;
                    break;
                }
            }

            if (!b) {
                System.out.println("Вы не можете отбиться этой картой");
            } else {
                break;
            }
            correctNum = numberCard(hand.size(), true);

        }
        return correctNum;
    }

    public Card attack(Table table) {
        Card attackCard;
        if (table.getCardsOnTable().size() == 0) {
            attackCard = hand.remove(numberCard(hand.size(), false) -1);
        } else {
            int number = correctNumber(table);
            if (number == 0) {
                return null;
            }
            attackCard = hand.remove(number-1);
        }
        table.getCardsOnTable().add(attackCard);
        return attackCard;
    }

    @Override
    public boolean defend(Card attackCard, Table table) {
        int correctNum = numberCard(hand.size() , true);
        boolean b = false;
        while(true){
            if(correctNum == 0){
                return false;
            }
            Card defenceCard  = hand.get(correctNum- 1);

            if((defenceCard.getPower() > attackCard.getPower() && defenceCard.getSuit().equals(attackCard.getSuit())) || defenceCard.getSuit().equals(table.getTrumpCard().getSuit())){
                b = true;
                hand.remove(defenceCard);
                table.getCardsOnTable().add(defenceCard);
            }

            if(!b){
                System.out.println("You can not throw this card");
            }else {
                break;
            }
            correctNum = numberCard(hand.size() , true);

        }
        return true;
    }

    @Override
    public List<Card> getHand() {
        return hand;
    }
}
