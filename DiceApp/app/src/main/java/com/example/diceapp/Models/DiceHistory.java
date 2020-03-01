package com.example.diceapp.Models;

import java.util.ArrayList;

public class DiceHistory extends ArrayList<ArrayList<Dice>> {
    private static DiceHistory instance = new DiceHistory();
    private final static int FIRST_INDEX = 0;

    private DiceHistory(){ }

    public static DiceHistory getInstance(){
        return instance;
    }

    @Override
    public boolean add(ArrayList<Dice> rolls) {
        super.add(FIRST_INDEX, rolls);
        return true;
    }
}
