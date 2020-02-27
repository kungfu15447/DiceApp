package com.example.diceapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.diceapp.Models.CustomAdapter;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrInterface;

public class HistoryActivity extends AppCompatActivity {
    private SlidrInterface slidr;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        listView = findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter(this, R.layout.activity_history);
        listView.setAdapter(customAdapter);
        slidr = Slidr.attach(this);
    }

    public void lockSlide(View v){
        slidr.lock();
    }

    public void unlockSlide(View v){
        slidr.unlock();
    }

}
