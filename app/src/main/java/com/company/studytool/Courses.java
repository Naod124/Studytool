package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Courses extends AppCompatActivity {
Button moveToQuiz;
Button moveToDigital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        moveToQuiz=findViewById(R.id.moveToQuiz);
        moveToQuiz.setOnClickListener(v -> startActivity(new Intent(Courses.this, Quiz.class)));

        moveToDigital= findViewById(R.id.digital);
        moveToDigital.setOnClickListener(v -> startActivity(new Intent(Courses.this,DigitalCourse.class)));
    }
}