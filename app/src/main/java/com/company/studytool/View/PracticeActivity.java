package com.company.studytool.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.company.studytool.R;

public class PracticeActivity extends AppCompatActivity {
    private Button startQuiz;
    private Button truthTable;
    private String courseName = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_quiz_activity);
        startQuiz = findViewById(R.id.startQuiz);
        truthTable = findViewById(R.id.truthTable);
        Intent intent = getIntent();
        courseName = intent.getStringExtra("course");
        if (courseName.equals("Digital System Design")) {
            truthTable.setVisibility(View.VISIBLE);
            startQuiz.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, QuizActivity.class).putExtra("course", "Digital System Design")));
            truthTable.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, TruthTableActivity.class).putExtra("course", "Digital System Design")));
        } else if (courseName.equals("Data Structure")) {
            startQuiz.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, QuizActivity.class).putExtra("course", "Data Structure")));
        } else if (courseName.equals("Data Communication")) {
            startQuiz.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, QuizActivity.class).putExtra("course", "Data Communication")));
        } else if (courseName.equals("Operating System")) {
            startQuiz.setOnClickListener(v -> startActivity(new Intent(PracticeActivity.this, QuizActivity.class).putExtra("course", "Operating System")));
        }

    }
}
