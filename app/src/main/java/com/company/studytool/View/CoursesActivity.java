package com.company.studytool.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.company.studytool.R;

public class CoursesActivity extends AppCompatActivity implements View.OnClickListener {
    Button moveToDS;
    Button moveToOS;
    Button moveToDC;
    Button moveToDigital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        moveToDS = findViewById(R.id.moveToDS);
        moveToDC = findViewById(R.id.moveToDC);
        moveToOS = findViewById(R.id.moveToOS);
        moveToDigital = findViewById(R.id.moveToDigital);
//        moveToDS.setOnClickListener(v -> startActivity(new Intent(Courses.this, DsCourseActivity.class)));
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == moveToDS.getId()) {
            Intent intent = new Intent(CoursesActivity.this, SelectedCourseActivity.class);
            intent.putExtra("course", "Data Structure");
            startActivity(intent);
        } else if (v.getId() == moveToDC.getId()) {
            Intent intent = new Intent(CoursesActivity.this, SelectedCourseActivity.class);
            intent.putExtra("course", "Data Communication");
            startActivity(intent);

        } else if (v.getId() == moveToOS.getId()) {

            Intent intent = new Intent(CoursesActivity.this, SelectedCourseActivity.class);
            intent.putExtra("course", "Operating System");
            startActivity(intent);

        } else if (v.getId() == moveToDigital.getId()) {
            Intent intent = new Intent(CoursesActivity.this, SelectedCourseActivity.class);
            intent.putExtra("course", "Digital System Design");
            startActivity(intent);
        }
    }

}