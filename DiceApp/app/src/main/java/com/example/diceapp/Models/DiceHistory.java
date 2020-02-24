package com.example.diceapp.Models;

import java.util.ArrayList;

public class DiceHistory extends ArrayList<ArrayList<Dice>> {
    private static DiceHistory instance = new DiceHistory();

    private DiceHistory(){ }

    public static DiceHistory getInstance(){
        return instance;
    }

    @Override
    public boolean add(ArrayList<Dice> rolls) {
        return super.add(rolls);
    }
}
