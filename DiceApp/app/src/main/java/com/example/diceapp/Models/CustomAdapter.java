package com.example.diceapp.Models;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.diceapp.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Dice> {
    DiceHistory dH;
    DisplayMetrics displayMetrics;
    WindowManager w;

    public CustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        displayMetrics = new DisplayMetrics();

    }

    @Override
    public int getCount() {
        dH = DiceHistory.getInstance();
        return dH.size();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    LayoutInflater getLayoutInflater(){
     return (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(int position,View view, ViewGroup parent){
        dH = DiceHistory.getInstance();
        if (view == null) {
            view = getLayoutInflater().inflate(R.layout.list_item_layout, null);
        }

        TextView textView = view.findViewById(R.id.txtNumber);

        LinearLayout dices = view.findViewById(R.id.ll_eyes);
        int width = 200;
        int height = width;
        dices.removeAllViews();
        textView.setText("Roll number: " + (dH.size() - position));
            for (Dice dice: dH.get(position)) {
                int eyes = dice.diceNumber;
                int[] x = {R.drawable.one, R.drawable.two,R.drawable.three, R.drawable.four, R.drawable.five,R.drawable.six};
                ImageView diceFrame = new ImageView(getContext());
                diceFrame.setImageResource(x[eyes-1]);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width,height);
                diceFrame.setLayoutParams(params);
                dices.addView(diceFrame);
            }
            return view;
    }

    public void clearData() {
        dH.clear();
    }
}
