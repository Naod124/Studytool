package com.company.studytool;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Collections;

import static com.company.studytool.Quiz.getScore;

public class ShowScoreActivity extends AppCompatActivity {
    TextView score;
    Button done;
    String courseName = "";

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_score_activity);
        score = findViewById(R.id.scoreView);
        done = findViewById(R.id.done);

        Intent intent = getIntent();
        String scoreConverted = intent.getStringExtra("score");
        courseName = intent.getStringExtra("course");

        System.out.println(scoreConverted);
        try {
            score.setText("Your high score " + Collections.max(getScore(courseName)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
