package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Quiz extends AppCompatActivity {

    private Button StartQuiz1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);
        StartQuiz1=findViewById(R.id.startQuiz1);
        StartQuiz1.setOnClickListener(v -> startActivity(new Intent(Quiz.this, QuizQuestion.class)));


    }
}