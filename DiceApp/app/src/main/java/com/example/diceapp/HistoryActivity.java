package com.example.diceapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.diceapp.Models.CustomAdapter;
import com.example.diceapp.Models.Dice;
import com.example.diceapp.Models.DiceHistory;

import java.util.ArrayList;
//import com.r0adkll.slidr.Slidr;
//import com.r0adkll.slidr.model.SlidrInterface;

public class HistoryActivity extends AppCompatActivity {
    //private SlidrInterface slidr;
    private ListView listView;
    private ArrayList<ArrayList<Dice>> dieHistory;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = findViewById(R.id.listView);
        customAdapter = new CustomAdapter(this, R.layout.activity_history);
        listView.setAdapter(customAdapter);
        dieHistory = DiceHistory.getInstance();
        //slidr = Slidr.attach(this);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        listView = findViewById(R.id.listView);
        customAdapter = new CustomAdapter(this, R.layout.activity_history);
        listView.setAdapter(customAdapter);
    }

    public void onClickClear(View view) {
        customAdapter.clearData();
        customAdapter.notifyDataSetChanged();
    }

    /*public void lockSlide(View v){
        slidr.lock();
    }

    public void unlockSlide(View v){
        slidr.unlock();
    }*/

}
