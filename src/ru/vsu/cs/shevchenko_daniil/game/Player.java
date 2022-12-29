package ru.vsu.cs.shevchenko_daniil.game;

import ru.vsu.cs.shevchenko_daniil.enums.PlayerState;
import ru.vsu.cs.shevchenko_daniil.enums.PlayerStatus;

import java.util.List;

public interface Player {

    boolean isBot();

    void setId(int id);

    PlayerState getState();

    void takeCard(Table table);

    void takeCardFromTable(Table table);

    PlayerStatus getStatus();

    void setStatus(PlayerStatus status);

    void setState(PlayerState state);

    String getName();

    int getId();

    Card attack(Table table);

    boolean defend(Card attackCard, Table table);

    List<Card> getHand();

}
