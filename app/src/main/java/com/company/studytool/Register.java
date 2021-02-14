package com.company.studytool;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Scene;
import android.transition.TransitionInflater;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.transition.Transition;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.firebase.client.Firebase;

import org.json.JSONException;
import org.json.JSONObject;

public class Register extends AppCompatActivity {





    EditText username, password, email;

    Button registerButton;
    String user,pass,Email;
    TextView login;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);




        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        email = (EditText)findViewById(R.id.email);
        registerButton = (Button)findViewById(R.id.groupchat);
        login = (TextView)findViewById(R.id.login);

        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, login.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                pass = password.getText().toString();
                Email = email.getText().toString();

                if(user.equals("")){
                    username.setError("It is empty");
                }
                else if(pass.equals("")){
                    password.setError("It is empty");
                }
                else if(Email.equals("")){
                    email.setError("It is empty");
                }
                else if(!user.matches("[A-Za-z0-9]+")){
                    username.setError("only alphabet or number allowed");
                }
                else if(user.length()<5){
                    username.setError("at least 5 characters long");
                }
                else if(pass.length()<5){
                    password.setError("at least 5 characters long");
                }
                else if (!(Email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"))){
                    email.setError("The format of email is not correct");
                }
                else {
                    final ProgressDialog pd = new ProgressDialog(Register.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://mystudytool-7b85e-default-rtdb.firebaseio.com/Users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://mystudytool-7b85e-default-rtdb.firebaseio.com/Users");

                            switch (s) {
                                case "null":
                                    reference.child(user).child("password").setValue(pass);
                                    Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                    break;
                                default:
                                    try {
                                        JSONObject obj = new JSONObject(s);

                                        if (!obj.has(user)) {
                                            reference.child(user).child("Password").setValue(pass);
                                            reference.child(user).child("Email").setValue(Email);
                                            Toast.makeText(Register.this, "registration successful", Toast.LENGTH_LONG).show();
                                            username.getText().clear();
                                            password.getText().clear();
                                            email.getText().clear();
                                        } else {
                                            Toast.makeText(Register.this, "username already exists", Toast.LENGTH_LONG).show();
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

                    RequestQueue rQueue = Volley.newRequestQueue(Register.this);
                    rQueue.add(request);
                }
            }
        });

    }



}