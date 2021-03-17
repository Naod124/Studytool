package com.company.studytool.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.company.studytool.R;
import com.facebook.FacebookSdk;


public class MenuActivity extends AppCompatActivity {

    Button courses, privateChat, todoList;
    Button buttonPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.activity_menu);

        privateChat = findViewById(R.id.privatechat);
        todoList = findViewById(R.id.todo_list);
        courses = findViewById(R.id.coursesList);
        courses.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, CoursesActivity.class)));
        privateChat.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, StudentListActivity.class)));
        todoList.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, MyToDoListActivity.class)));
        buttonPhoto= (Button)findViewById(R.id.btnPhoto1);
        buttonPhoto.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, FacebookActivity.class)));

    }

    }
