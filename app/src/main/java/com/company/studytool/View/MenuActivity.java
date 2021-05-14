package com.company.studytool.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.company.studytool.R;
import com.facebook.FacebookSdk;
import com.google.android.material.navigation.NavigationView;


public class MenuActivity extends AppCompatActivity {

    private Button courses, privateChat, todoList;
    private DrawerLayout drawerLayout;
    private Button buttonPhoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        setContentView(R.layout.drawer_menu);
        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerLayout);
        NavigationView navigationView = findViewById(R.id.navView);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.courses:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fragmentsHolder, new CourseFragment()).commit();
                        break;
                    case R.id.private_chat:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fragmentsHolder, new student_list()).commit();
                        break;
                    case R.id.my_notes:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fragmentsHolder, new ToDolistFragment()).commit();
                        break;
                    case R.id.share_to_facebook:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fragmentsHolder, new Facebook()).commit();
                        break;
                    case R.id.buy_sell:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fragmentsHolder, new BuySellFragment()).commit();
                        break;
                    case R.id.project_manager:
                        getSupportFragmentManager().beginTransaction().
                                replace(R.id.fragmentsHolder, new ProjectManagerFragment()).commit();
                        break;
                    case R.id.mail:
                        break;
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragmentsHolder, new CourseFragment()).commit();
            navigationView.setCheckedItem(R.id.courses);
        }
//        privateChat = findViewById(R.id.privatechat);
//        todoList = findViewById(R.id.todo_list);
//        courses = findViewById(R.id.coursesList);
//        courses.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, CoursesActivity.class)));
//        privateChat.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, StudentListActivity.class)));
//        todoList.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, MyToDoListActivity.class)));
//        buttonPhoto= (Button)findViewById(R.id.btnPhoto1);
//        buttonPhoto.setOnClickListener(v -> startActivity(new Intent(MenuActivity.this, FacebookActivity.class)));

    }

}
