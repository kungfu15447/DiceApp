package com.example.diceapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.diceapp.Models.Dice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
