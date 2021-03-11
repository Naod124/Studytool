package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import com.facebook.FacebookSdk;


public class Menu extends AppCompatActivity {

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
        courses.setOnClickListener(v -> startActivity(new Intent(Menu.this, Courses.class)));
        privateChat.setOnClickListener(v -> startActivity(new Intent(Menu.this, StudentList.class)));
        todoList.setOnClickListener(v -> startActivity(new Intent(Menu.this, MyToDoList.class)));
        buttonPhoto= (Button)findViewById(R.id.btnPhoto1);
        buttonPhoto.setOnClickListener(v -> startActivity(new Intent(Menu.this,Facebook.class)));

    }

    }
