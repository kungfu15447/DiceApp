package com.example.diceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.diceapp.Models.Dice;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> die;
    ArrayList<ArrayList<Dice>> dieHistory;
    Button btnRoll;
    Button btnIncrement;
    Button btnDecrement;
    LinearLayout linearDie;
    boolean rolled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRoll = findViewById(R.id.btnRoll);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnDecrement = findViewById(R.id.btnDecrement);
        linearDie = findViewById(R.id.linearDie);
        die = new ArrayList<>();
        dieHistory = DiceHistory.getInstance();
    }

    public void onClickRoll(View view) {
        if (!rolled) {
            rolled = true;
            ArrayList<Dice> rolls = new ArrayList<>();
            Random rng = new Random();
            for (ImageView diceView : die) {
                int roll = rng.nextInt(6) + 1;
                switch (roll) {
                    case 1:
                        diceView.setImageResource(R.drawable.one);
                        break;
                    case 2:
                        diceView.setImageResource(R.drawable.two);
                        break;
                    case 3:
                        diceView.setImageResource(R.drawable.three);
                        break;
                    case 4:
                        diceView.setImageResource(R.drawable.four);
                        break;
                    case 5:
                        diceView.setImageResource(R.drawable.five);
                        break;
                    case 6:
                        diceView.setImageResource(R.drawable.six);
                        break;
                }
                Dice dice = new Dice(diceView, roll);
                rolls.add(dice);
            }
            rolled = false;
        }
    }

    private void addToHistory(ArrayList<Dice> rolls) {
        dieHistory.add(rolls);
    }

    public void incrementDie(View view) {
        ImageView dice = new ImageView(getBaseContext());
        dice.setImageResource(R.drawable.question);
        int width = 200;
        int height = 200;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
        dice.setLayoutParams(params);

        if (checkIfLayoutIsThicc(linearDie) && !checkIfLayoutIsSlim(linearDie)) {
            LinearLayout diceLayout = new LinearLayout(this);
            int layoutWidth = linearDie.getWidth();
            int layoutHeight = linearDie.getHeight() / 3;
            LinearLayout.LayoutParams diceLayoutParams = new LinearLayout.LayoutParams(layoutWidth, layoutHeight);
            diceLayout.setLayoutParams(diceLayoutParams);
            linearDie.addView(diceLayout);
        }

        if (!checkIfLayoutIsThicc(linearDie)) {
            LinearLayout lastLayout = (LinearLayout) linearDie.getChildAt(linearDie.getChildCount()-1);
            lastLayout.addView(dice);
            die.add(dice);
        }

    }

    private boolean checkIfLayoutIsThicc(LinearLayout linearLayout) {
        double totalWidth = 0;
        double totalWidthWithExtraDice = 0;
        double layoutWidth = linearLayout.getWidth();
        if (linearLayout.getChildCount() == 0) {
            return true;
        }else {
            LinearLayout lastLayout = (LinearLayout) linearLayout.getChildAt(linearLayout.getChildCount()-1);
            for (int i = 0; i < lastLayout.getChildCount(); i++) {
              ImageView dice = (ImageView) lastLayout.getChildAt(i);
              totalWidth += dice.getWidth();
              if (i == lastLayout.getChildCount() - 1) {
                  totalWidthWithExtraDice = totalWidth + dice.getWidth();
              }
            }
            if (totalWidthWithExtraDice < layoutWidth) {
                return false;
            }else {
                return true;
            }
        }
    }

    private boolean checkIfLayoutIsSlim(LinearLayout linearLayout) {
        double totalHeight = 0;
        double totalHeightWithExtraLayout = 0;
        double layoutHeight = linearLayout.getHeight();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            LinearLayout currentLayout = (LinearLayout) linearLayout.getChildAt(i);
            totalHeight += currentLayout.getHeight();
            if (i == linearLayout.getChildCount() - 1) {
                totalHeightWithExtraLayout = totalHeight + currentLayout.getHeight();
            }
        }
        if (totalHeightWithExtraLayout < layoutHeight) {
            return false;
        }else {
            return true;
        }
    }

    public void decrementDie(View view) {
        if (die.size() != 0) {
            LinearLayout lastLayout = (LinearLayout) linearDie.getChildAt(linearDie.getChildCount() - 1);
            ImageView dice = (ImageView) lastLayout.getChildAt(lastLayout.getChildCount()-1);
            lastLayout.removeView(dice);
            if (lastLayout.getChildCount() == 0) {
                linearDie.removeView(lastLayout);
            }
            die.remove(dice);
        }
    }

    public void showHistory(View view){
        Intent intent = new Intent(this, HistoryActivity.class);
        
        startActivity(intent);
    }
}