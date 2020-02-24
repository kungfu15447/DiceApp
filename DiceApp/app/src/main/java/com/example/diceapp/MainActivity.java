package com.example.diceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ArrayList<ImageView> die;
    TextView[] histories;
    Button btnRoll;
    Button btnClear;
    Button btnIncrement;
    Button btnDecrement;
    LinearLayout linearHistory;
    LinearLayout linearDie;
    boolean rolled = false;
    int historyIndex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRoll = findViewById(R.id.btnRoll);
        btnClear = findViewById(R.id.btnClear);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnDecrement = findViewById(R.id.btnDecrement);
        linearHistory = findViewById(R.id.linearHistory);
        linearDie = findViewById(R.id.linearDie);
        die = new ArrayList<>();
        histories = new TextView[5];
    }

    public void onClickRoll(View view) {
        if (!rolled) {
            rolled = true;
            Random rng = new Random();
            String rollHistory = "";
            for (ImageView dice : die) {
                int roll = rng.nextInt(6)+ 1;
                switch(roll) {
                    case 1:
                        dice.setImageResource(R.drawable.one);
                        break;
                    case 2:
                        dice.setImageResource(R.drawable.two);
                        break;
                    case 3:
                        dice.setImageResource(R.drawable.three);
                        break;
                    case 4:
                        dice.setImageResource(R.drawable.four);
                        break;
                    case 5:
                        dice.setImageResource(R.drawable.five);
                        break;
                    case 6:
                        dice.setImageResource(R.drawable.six);
                        break;
                }
                if (rollHistory.isEmpty()) {
                    rollHistory = rollHistory + "" + roll;
                }else {
                    rollHistory = rollHistory + " + " + roll;
                }
            }
            if (!rollHistory.isEmpty()) {
                addToHistory("" + historyIndex + "." + " " + rollHistory);
                historyIndex++;
            }
        }
        rolled = false;
    }

    private void addToHistory(String rollHistory) {
        TextView history = new TextView(getBaseContext());
        history.setText(rollHistory);
        history.setTextSize(24);

            TextView currentView = new TextView(getBaseContext());
            TextView nextView = new TextView(getBaseContext());
            for (int i = 0; i < histories.length; i++) {
                if (i == 0) {
                    currentView = histories[i];
                    histories[i] = history;
                    nextView = histories[i+1];
                }else if (i == histories.length - 1){
                    histories[histories.length-1] = currentView;
                } else {
                    histories[i] = currentView;
                    currentView = nextView;
                    nextView = histories[i+1];
                }
            }
        linearHistory.removeAllViews();
        for (int i = 0; i < histories.length; i++) {
            if (histories[i] != null) {
                linearHistory.addView(histories[i]);
            }

        }
    }

    public void clearHistory(View view) {
        linearHistory.removeAllViews();
        historyIndex = 1;
        histories = new TextView[5];
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
        double totalHeighWithExtraLayout = 0;
        double layoutHeigh = linearLayout.getHeight();
        for (int i = 0; i < linearLayout.getChildCount(); i++) {
            LinearLayout currentLayout = (LinearLayout) linearLayout.getChildAt(i);
            totalHeight += currentLayout.getHeight();
            if (i == linearLayout.getChildCount() - 1) {
                totalHeighWithExtraLayout = totalHeight + currentLayout.getHeight();
            }
        }
        if (totalHeighWithExtraLayout < layoutHeigh) {
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
}