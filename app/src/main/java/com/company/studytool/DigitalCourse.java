package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DigitalCourse extends AppCompatActivity {
    Button video1;
    Button practise;
    Button book1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_course);
        video1=findViewById(R.id.tVideos);
        video1.setOnClickListener(v -> startActivity(new Intent(DigitalCourse.this, DigitalVideo.class)));
        practise=findViewById(R.id.practice);
        practise.setOnClickListener(v -> startActivity(new Intent(DigitalCourse.this, TruthTable.class)));
        book1= findViewById(R.id.book);
        book1.setOnClickListener(v -> startActivity(new Intent(DigitalCourse.this, DigitalBook.class)));

    }

}