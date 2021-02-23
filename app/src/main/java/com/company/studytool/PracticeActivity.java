package com.company.studytool;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PracticeActivity extends AppCompatActivity {
    Button startQuiz;
    Button truthTable;
    String courseName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_activity);
        startQuiz = findViewById(R.id.startQuiz);
        truthTable = findViewById(R.id.truthTable);
        Intent intent = getIntent();
        courseName = intent.getStringExtra("course");
        if (courseName.equals("Digital System Design")) {
            truthTable.setVisibility(View.VISIBLE);
            startQuiz.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, Quiz.class).putExtra("course", "Digital System Design")));
            truthTable.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, TruthTable.class).putExtra("course", "Digital System Design")));
        } else if (courseName.equals("Data Structure")) {
            startQuiz.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, Quiz.class).putExtra("course", "Data Structure")));
        } else if (courseName.equals("Data Communication")) {
            startQuiz.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, Quiz.class).putExtra("course", "Data Communication")));
        } else if (courseName.equals("Operating System")) {
            startQuiz.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, Quiz.class).putExtra("course", "Operating System")));
        }

    }
}
