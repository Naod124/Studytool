package com.company.studytool.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.company.studytool.R;

public class StartQuizActivity extends AppCompatActivity {
    Button startQuiz;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_quiz_activity);
         startQuiz = findViewById(R.id.startQuiz);
        startQuiz.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
    }
}
