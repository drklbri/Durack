package ru.vsu.cs.shevchenko_daniil.game;

import java.util.List;

public class PrintStuff {

    public static void printHand(List<Card> hand){
        System.out.println("Ваша рука:");
        for (Card card: hand) {
            System.out.print(card.getRank().getName() + card.getSuit().getSuit() + " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printTable(List<Card> hand) {
        for (Card card: hand) {
            System.out.print(card.getRank().getName() + card.getSuit().getSuit() + " ");
        }
        System.out.println();
        System.out.println();
    }

    public static void printTrump(Table table) {
        System.out.println("Козырь: " + table.getTrumpCard().toString());
    }

    public static void printMove(Table table, Player player) {
        System.out.println();
        System.out.println("Козырь : " + table.getTrumpCard() + " Карт в колоде : " + table.getDeck().size() + "  ");
        System.out.print(" Стол :  ");
        printTable(table.getCardsOnTable());
        printHand(player.getHand());
        System.out.println("Введите номер карты (0 чтобы пасовать или взять карты) : ");
    }

    public static void printBotMove(Table table) {
        System.out.println();
        System.out.print(" Стол :  ");
        printTable(table.getCardsOnTable());
    }



}
