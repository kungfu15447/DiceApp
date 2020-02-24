package com.example.diceapp;

import java.util.ArrayList;
import java.util.List;

public class DiceHistory extends ArrayList {
    private static DiceHistory instance = new DiceHistory();

    private DiceHistory(){ }

    public static DiceHistory getInstance(){
        return instance;
    }

}
