package com.example.diceapp.Models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    public CustomAdapter(@NonNull Context context, int resource) {
        super(context, resource);

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
        dices.removeAllViews();
        textView.setText("Roll number: ");
            for (Dice dice: dH.get(position)) {
                ImageView imageViewToSet = new ImageView(getContext());
                int eyes = dice.diceNumber;
                int[] x = {R.drawable.one, R.drawable.two,R.drawable.three, R.drawable.four, R.drawable.five,R.drawable.six};
                imageViewToSet.setImageResource(x[eyes]);
                dices.addView(imageViewToSet);
            }
            return view;
    }

    public void clearData() {
        dH.clear();
    }
}
