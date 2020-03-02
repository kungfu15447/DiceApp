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
    int[] diceHead;
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
        diceHead = new int[]{
                R.drawable.one,
                R.drawable.two,
                R.drawable.three,
                R.drawable.four,
                R.drawable.five,
                R.drawable.six};
    }

    public void onClickRoll(View view) {
        if (die.size() != 0) {
            if (!rolled) {
                rolled = true;
                ArrayList<Dice> rolls = new ArrayList<>();
                Random rng = new Random();
                for (ImageView diceView : die) {
                    int roll = rng.nextInt(6) + 1;
                    diceView.setImageResource(diceHead[roll-1]);
                    diceView.setTag(diceHead[roll-1]);
                    Dice dice = new Dice(diceView, roll);
                    rolls.add(dice);
                }
                addToHistory(rolls);
                rolled = false;
            }
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
                            if (diceTags[i] != null && !diceTags[i].isEmpty()) {
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
                    }
                });
    }
}