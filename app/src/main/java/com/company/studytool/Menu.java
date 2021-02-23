package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class Menu extends AppCompatActivity {
    Button courses,privateChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        privateChat = findViewById(R.id.privatechat);
        courses=findViewById(R.id.coursesList);
        courses.setOnClickListener(v -> startActivity(new Intent(Menu.this, Courses.class)));
        privateChat.setOnClickListener(v -> startActivity(new Intent(Menu.this, StudentList.class)));
    }
}