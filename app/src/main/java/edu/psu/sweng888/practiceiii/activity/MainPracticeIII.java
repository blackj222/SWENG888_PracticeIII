package edu.psu.sweng888.practiceiii.activity;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import edu.psu.sweng888.practiceiii.R;

public class MainPracticeIII extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practiceiii_main);

        View recyclerView = findViewById(R.id.recyclerView);
    }
}