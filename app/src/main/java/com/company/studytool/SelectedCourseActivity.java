package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SelectedCourseActivity extends AppCompatActivity {
    Button video1;
    Button practise;
    Button book1;
    TextView courseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_course);
        courseName = findViewById(R.id.courseName);
        Intent intent = getIntent();
        courseName.setText(intent.getStringExtra("course"));
        video1 = findViewById(R.id.tVideos);
        video1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalVideo.class)));
        practise = findViewById(R.id.practice);
        book1 = findViewById(R.id.book);
        if (courseName.getText().equals("Data Structure")) {
            book1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalBook.class).putExtra("course", "Data Structure")));
            practise.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, PracticeActivity.class).putExtra("course", "Data Structure")));
            video1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalVideo.class).putExtra("course", "Data Structure")));
        } else if (courseName.getText().equals("Data Communication")) {
            book1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalBook.class).putExtra("course", "Data Communication")));
            practise.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, PracticeActivity.class).putExtra("course", "Data Communication")));
            video1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalVideo.class).putExtra("course", "Data Communication")));
        } else if (courseName.getText().equals("Operating System")) {
            book1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalBook.class).putExtra("course", "Operating System")));
            practise.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, PracticeActivity.class).putExtra("course", "Operating System")));
            video1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalVideo.class).putExtra("course", "Operating System")));
        } else if (courseName.getText().equals("Digital System Design")) {
            book1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalBook.class).putExtra("course", "Digital System Design")));
            practise.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, PracticeActivity.class).putExtra("course", "Digital System Design")));
            video1.setOnClickListener(v -> startActivity(new Intent(SelectedCourseActivity.this, DigitalVideo.class).putExtra("course", "Digital System Design")));
        }
    }

}