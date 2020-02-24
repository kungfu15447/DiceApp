package com.example.diceapp.Models;

import android.widget.ImageView;

public class Dice {
    public ImageView diceHead;
    public int diceNumber;

    public Dice(ImageView diceHead, int diceNumber) {
        this.diceHead = diceHead;
        this.diceNumber = diceNumber;
    }

    public int getDiceNumber() {
        return diceNumber;
    }

    public ImageView getDiceHead() {
        return diceHead;
    }

    public void setDiceHead(ImageView diceHead) {
        this.diceHead = diceHead;
    }

    public void setDiceNumber(int diceNumber) {
        this.diceNumber = diceNumber;
    }
}
