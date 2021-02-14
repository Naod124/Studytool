package com.company.studytool;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class login extends AppCompatActivity {

    TextView register;
    EditText username, password;
    Button loginButton;
    String user, pass;
    RequestQueue rQueue;
    ProgressDialog pd;
    String url;
    String textFile = "com.company.studytool.Q.text";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);

        register.setOnClickListener(v -> startActivity(new Intent(login.this, Register.class)));

        loginButton.setOnClickListener(v -> {
            Question question = new Question();
            question.setMainQuestion("The time factor when determining the efficiency of algorithm is measured by");
            question.setAlternative1("Counting microseconds");
            question.setAlternative2("Counting the number of statements");
            question.setAlternative3("Counting the kilobytes of algorithm");
            question.setRight("Counting the number of key operations");






                addQuiz(" Algorithms and Data Structures", "Algorithm analysis", question);


            user = username.getText().toString();
            pass = password.getText().toString();

            if (user.equals("")) {
                username.setError("can't be blank");
            } else if (pass.equals("")) {
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


    public void addQuiz(String courseName, String QuizTitle, Question question) {
        Firebase.setAndroidContext(getApplicationContext());
        final ProgressDialog pd = new ProgressDialog(login.this);
        pd.setMessage("Loading...");
        pd.show();

        String url = "https://mystudytool-7b85e-default-rtdb.firebaseio.com/Quizz.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Firebase reference = new Firebase("https://mystudytool-7b85e-default-rtdb.firebaseio.com/Quizz");

                switch (s) {
                    case "null":
                        reference.child(courseName).child(QuizTitle).child(question.mainQuestion).setValue(question);

                        Toast.makeText(login.this, "done", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        try {
                            JSONObject obj = new JSONObject(s);

                            if (!obj.has(String.valueOf(question))) {
                                reference.child(courseName).child(QuizTitle).setValue(question);
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




}











