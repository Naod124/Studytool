package com.company.studytool;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class login extends AppCompatActivity {

    TextInputLayout username, password;
    Button loginButton;
    String user, pass;
    RequestQueue rQueue;
    ProgressDialog pd;
    String url;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (TextInputLayout) findViewById(R.id.username);
        password = (TextInputLayout) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
/*
This will add quiz to DB
            ArrayList<Question> qList = getQlist();
            for (int i = 0; i < qList.size(); i++) {
                addQuiz(" Algorithms and Data Structures", "Algorithm analysis", qList.get(i), i+1);
            }

*/

            user = username.getEditText().getText().toString().trim();
            pass = password.getEditText().getText().toString().trim();
            if (user.isEmpty()) {
                username.setError("can't be blank");
            } else if (pass.isEmpty()) {
                password.setError("can't be blank");
            } else {
                url = getString(R.string.Db);
                pd = new ProgressDialog(login.this);
                pd.setMessage("Loading...");
                pd.show();

                StringRequest request = new StringRequest(Request.Method.GET, url, s -> {
                    if ("null".equals(s)) {
                        Toast.makeText(login.this, "user not found", Toast.LENGTH_LONG).show();
                    } else {
                        try {
                            JSONObject obj = new JSONObject(s);

                            if (!obj.has(user)) {
                                Toast.makeText(login.this, "user not found", Toast.LENGTH_LONG).show();

                                username.setError("User is not found");
                            } else if (obj.getJSONObject(user).getString("Password").equals(pass)) {
                                StudentDetails.username = user;
                                StudentDetails.password = pass;
                                startActivity(new Intent(login.this, Menu.class));
                            } else {
                                password.setError("password is incorrect");

                                Toast.makeText(login.this, "incorrect password", Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    pd.dismiss();
                }, volleyError -> {
                    System.out.println("" + volleyError);
                    pd.dismiss();
                });

                rQueue = Volley.newRequestQueue(login.this);
                rQueue.add(request);
            }

        });
    }

//    private ArrayList getQlist() {
//        ArrayList myList = new ArrayList();
//        Question question1 = new Question("Two main measures for the efficiency of an algorithm are", "Processor and memory", "Complexity and capacity", "Data and space", "Time and space");
//        Question question2 = new Question("The time factor when determining the efficiency of algorithm is measured by", "Counting the kilobytes of algorithm", "Counting the number of statements", "Counting microseconds", "Counting the number of key operations");
//        Question question3 = new Question("The space factor when determining the efficiency of algorithm is measured by", "Counting the maximum disk space needed by the algorithm", "Counting the average memory needed by the algorithm", "Counting the minimum memory needed by the algorithm", "Counting the maximum memory needed by the algorithm");
//        Question question4 = new Question("Which of the following cases does not exist in complexity theory", "Best case ", "Worst case", "Average case", "Null case");
//        Question question5 = new Question("The Worst case occur in linear search algorithm when", " Item is somewhere in the middle of the array", "Item is not in the array at all ", " Item is the last element in the array ", "Item is the last element in the array or is not there at all");
//        Question question6 = new Question("The Average case occur in linear search algorithm", "When Item is the last element in the array or is not there at all", "When Item is the last element in the array ", "When Item is not in the array at all ", "When Item is somewhere in the middle of the array");
//        Question question7 = new Question("The complexity of the average case of an algorithm is", "Much more simpler to analyze than that of worst case", "Sometimes more complicated and some other times simpler than that of worst case", "None of them ", "Much more complicated to analyze than that of worst case");
//        Question question8 = new Question("The complexity of linear search algorithm is", "O(log n)", "O(n2)", "O(n log n) ", "O(n)");
//        Question question9 = new Question("The complexity of Binary search algorithm is", "O(n)", "O(n log n)", "O(n2)", "O(log n)");
//        Question question10 = new Question(" The complexity of Bubble sort algorithm is", "O(n log n)", "O(log n)", "O(n)", "O(n2)");
//        Question question11 = new Question("The complexity of merge sort algorithm is", "O(n)", "O(log n)", "O(n2)", "O(n log n)");
//        Question question12 = new Question(" The complexity of selection sort algorithm is", "O(log n)", "O(n)", "O(n log n)", "O(n2)");
//        Question question13 = new Question("What is the worst case running time complexity of merge sort?", "O(n2)", "O(n)", "O(log n)", "O(n log n)");
//        Question question14 = new Question("What is the best case running time complexity of quicksort?", "O(n2)", "O(n)", "O(log n)", "O(n log n)");
//        Question question15 = new Question("What is the average case running time complexity of quicksort?", "O(n2)", "O(n)", "O(log n)", "O(n log n)");
//        Question question16 = new Question("The best case running time  complexity of insertion sort algorithm is", "O(n log n)", "O(n2)", "O(log n)", "O(n)");
//        Question question17 = new Question("A sort which compares adjacent elements in a list and switches where necessary is", "selection sort ", "quick sort", "insertion sort", "bubble sort");
//        Question question18 = new Question("As part of the maintenance work, you are entrusted with the work of rearranging the library books in a shelf in proper order, at the end of each day. The ideal choice will be", "bubble sort", "Merge sort", "Selection sort", "Insertion sort");
//        Question question19 = new Question("Which among the following is the best when the list is already sorted", "Selection sort", " Merge sort", "Bubble sort", "Insertion sort");
//        Question question20 = new Question("You have a sorted array and now you are given an element to be placed in that array so that the resulting array is also sorted, the best sorting technique in this case is", " bubble sort", "merge sort", "quick sort", "Insertion sort");
//        Question question21 = new Question("The running time of quick sort largely depends on", "arrangement of elements", "size of element ", "number of inputs", "selection of pivot element ");
//        myList.add(question1);
//        myList.add(question2);
//        myList.add(question3);
//        myList.add(question4);
//        myList.add(question5);
//        myList.add(question6);
//        myList.add(question7);
//        myList.add(question8);
//        myList.add(question9);
//        myList.add(question10);
//        myList.add(question11);
//        myList.add(question12);
//        myList.add(question13);
//        myList.add(question14);
//        myList.add(question15);
//        myList.add(question16);
//        myList.add(question17);
//        myList.add(question18);
//        myList.add(question19);
//        myList.add(question20);
//        myList.add(question21);
//        return myList;
//    }


    public void addQuiz(String courseName, String QuizTitle, QuestionModel questionModel, int number) {
        Firebase.setAndroidContext(getApplicationContext());
        final ProgressDialog pd = new ProgressDialog(login.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://studytool-304217-default-rtdb.firebaseio.com/Quiz.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Firebase reference = new Firebase("https://studytool-304217-default-rtdb.firebaseio.com/Quiz");

                switch (s) {
                    case "null":
                        reference.child(courseName).child(QuizTitle).child(String.valueOf(number)).setValue(questionModel);

                        Toast.makeText(login.this, "done", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        try {
                            JSONObject obj = new JSONObject(s);

                            if (!obj.has(String.valueOf(questionModel))) {
                                reference.child(courseName).child(QuizTitle).child(String.valueOf(number)).setValue(questionModel);
                                Toast.makeText(login.this, "registration successful", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(login.this, "Quiz already exists", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                }
                pd.dismiss();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
                pd.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(login.this);
        rQueue.add(request);
    }


    public void forgotPassword(View view) {
        startActivity(new Intent(login.this, ForgotPassword.class));


    }







}
