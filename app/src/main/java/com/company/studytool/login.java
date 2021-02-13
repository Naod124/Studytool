package com.company.studytool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class login extends AppCompatActivity {

    TextView register;
    EditText username, password;
    Button loginButton;
    String user, pass;
    RequestQueue rQueue;
    ProgressDialog pd;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = (TextView)findViewById(R.id.register);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        loginButton = (Button)findViewById(R.id.loginButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this, Register.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();

                if(user.equals("")){
                    username.setError("can't be blank");
                }
                else if(pass.equals("")){
                    password.setError("can't be blank");
                }
                else{
                    url = getString(R.string.Db);
                    pd = new ProgressDialog(login.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            switch (s) {
                                case "null":
                                    Toast.makeText(login.this, "user not found", Toast.LENGTH_LONG).show();
                                    break;
                                default:
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
                                    break;
                            }

                            pd.dismiss();
                        }
                    },new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            System.out.println("" + volleyError);
                            pd.dismiss();
                        }
                    });

                     rQueue = Volley.newRequestQueue(login.this);
                    rQueue.add(request);
                }

            }
        });
    }

    public void addQuiz(String courseName,String QuizTitle,Question question){


        String url = "https://mystudytool-7b85e-default-rtdb.firebaseio.com/Courses/Quizz.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                Firebase reference = new Firebase("https://mystudytool-7b85e-default-rtdb.firebaseio.com/Courses/Quizz");

                switch (s) {
                    case "null":
                        reference.child(courseName).child(QuizTitle).setValue(question);
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

        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError );
                pd.dismiss();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(login.this);
        rQueue.add(request);
    }
    public void questionsToObject() throws IOException {

        FileInputStream fs= new FileInputStream("someFile.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));

        for(int i = 0; i < 1; ++i)
            br.readLine();
        String lineIWant = br.readLine();

    }
}



