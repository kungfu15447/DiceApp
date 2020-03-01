package com.example.diceapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.diceapp.Models.Dice;
import com.example.diceapp.Models.DiceHistory;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends Activity {

    ArrayList<ImageView> die;
    ArrayList<ArrayList<Dice>> dieHistory;
    Button btnRoll;
    Button btnIncrement;
    Button btnDecrement;
    LinearLayout linearDie;
    boolean rolled = false;
    private static final int MAXIMUM_DICE_LIMIT = 6;
    private static final int MINIMUM_DICE_LIMIT = 0;

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
                ImageView diceToHistory = new ImageView(getBaseContext());
                int roll = rng.nextInt(6) + 1;
                switch (roll) {
                    case 1:
                        diceView.setImageResource(R.drawable.one);
                        diceView.setTag(R.drawable.one);
                        diceToHistory.setImageResource(R.drawable.one);
                        diceToHistory.setTag(R.drawable.one);
                        break;
                    case 2:
                        diceView.setImageResource(R.drawable.two);
                        diceView.setTag(R.drawable.two);
                        diceToHistory.setImageResource(R.drawable.two);
                        diceToHistory.setTag(R.drawable.two);
                        break;
                    case 3:
                        diceView.setImageResource(R.drawable.three);
                        diceView.setTag(R.drawable.three);
                        diceToHistory.setImageResource(R.drawable.three);
                        diceToHistory.setTag(R.drawable.three);
                        break;
                    case 4:
                        diceView.setImageResource(R.drawable.four);
                        diceView.setTag(R.drawable.four);
                        diceToHistory.setImageResource(R.drawable.four);
                        diceToHistory.setTag(R.drawable.four);
                        break;
                    case 5:
                        diceView.setImageResource(R.drawable.five);
                        diceView.setTag(R.drawable.five);
                        diceToHistory.setImageResource(R.drawable.five);
                        diceToHistory.setTag(R.drawable.five);
                        break;
                    case 6:
                        diceView.setImageResource(R.drawable.six);
                        diceView.setTag(R.drawable.six);
                        diceToHistory.setImageResource(R.drawable.six);
                        diceToHistory.setTag(R.drawable.six);
                        break;
                }
                Dice dice = new Dice(diceToHistory, roll);
                rolls.add(dice);
            }
            addToHistory(rolls);
            rolled = false;
        }
    }

    private void addToHistory(ArrayList<Dice> rolls) {
        dieHistory.add(rolls);
    }

    public void incrementDie(View view) {
        if (die.size() < MAXIMUM_DICE_LIMIT) {
            ImageView dice = new ImageView(getBaseContext());
            dice.setImageResource(R.drawable.question);
            int width = linearDie.getWidth() / 6;
            int height = width;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
            dice.setLayoutParams(params);
            linearDie.addView(dice);
            dice.setTag(R.drawable.question);
            die.add(dice);
        }

    }

    public void decrementDie(View view) {
        if (die.size() != MINIMUM_DICE_LIMIT) {
            linearDie.removeViewAt(linearDie.getChildCount()-1);
            die.remove(die.size()-1);
        }
    }

    public void showHistory(View view){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        String[] diceTags = new String[die.size()];
        for (ImageView dice : die) {
            if (dice != null && dice.getTag() != null) {
                diceTags[die.indexOf(dice)] = dice.getTag().toString();
            }
        }
        outState.putStringArray("dieDrawable", diceTags);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        final String[] diceTags = savedInstanceState.getStringArray("dieDrawable");
        linearDie.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                            linearDie.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                        else {
                            linearDie.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                        }
                        for (int i = 0; i < diceTags.length; i++) {
                            ImageView dice = new ImageView(getBaseContext());
                            dice.setImageResource(Integer.parseInt(diceTags[i]));
                            int width = linearDie.getWidth() / 6;
                            int height = width;
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
                            dice.setLayoutParams(params);
                            linearDie.addView(dice);
                            die.add(dice);
                        }
                    }
                });
    }
}