package com.company.studytool.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.studytool.R;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.firebase.client.Firebase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;
import java.util.Random;
import java.util.regex.Pattern;

public class
RegisterActivity extends AppCompatActivity {

    Random rand = new Random();

    int randomCode = rand.nextInt(999999);

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{5,}" +               //at least 4 characters
                    "$");
    private static final Pattern Email_PATTERN =
            Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + //any character
                    "\\@" +                                         //@
                    "[stud]{4}" +                                   //stud after @
                    "(" +
                    "\\." +                                         //dot after stud
                    "[hkr]{3}" +                                    // hkr after dot
                    "\\." +                                         //dot after hkr
                    "[se]{2}" +                                     //se after dot
                    ")+");
    String passRules = "at least 1 digit\n" +
            "at least 1 lower case letter\n" +
            "at least 1 upper case letter\n" +
            "at least 1 special character\n" +
            "at least 4 characters\n" +
            "no white spaces";
    TextInputLayout username, password;
    Button registerButton;
    String user, pass, Email;
    TextView login;

    TextInputEditText email ;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = (TextInputLayout) findViewById(R.id.username);
        password = (TextInputLayout) findViewById(R.id.password);
        email =  findViewById(R.id.email);
        registerButton = (Button) findViewById(R.id.Confirm);
        login = (TextView) findViewById(R.id.login);
        Firebase.setAndroidContext(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, loginActivity.class));
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = Objects.requireNonNull(username.getEditText()).getText().toString().trim();
                pass = Objects.requireNonNull(password.getEditText()).getText().toString().trim();
                Email = Objects.requireNonNull(email.getText().toString().trim());

                if (user.isEmpty()) {
                    username.setError("It is empty");
                } else if (pass.isEmpty()) {
                    password.setError("It is empty");
                } else if (Email.isEmpty()) {
                    email.setError("It is empty");
                } else if (!user.matches("[A-Za-z0-9]+")) {
                    username.setError("only alphabet or number allowed");
                } else if (user.length() < 5) {
                    username.setError("at least 5 characters long");
//                }else if (pass.length() < 5){
                } /*else if (!PASSWORD_PATTERN.matcher(pass).matches()) {
                    password.setError(passRules);

                }*//* else if (!Email_PATTERN.matcher(Email).matches()) {
//                } else if (!(Email.matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$"))) {
                    email.setError("The format of email is not correct");
                }
                 */else {
                    final ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
                    pd.setMessage("Loading...");
                    pd.show();

                    String url = "https://studytool-304217-default-rtdb.firebaseio.com/Users.json";

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            Firebase reference = new Firebase("https://studytool-304217-default-rtdb.firebaseio.com/Users");

                            if ("null".equals(s)) {
                                reference.child(user).child("password").setValue(pass);
                                Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                            } else {
                                try {
                                    JSONObject obj = new JSONObject(s);
                                    TextInputEditText verify = findViewById(R.id.verifyCode);
                                    int verifyCode = Integer.parseInt(verify.getText().toString());
                                    if (verifyCode==randomCode) {
                                        if (!obj.has(user)) {
                                            reference.child(user).child("Password").setValue(pass);
                                            reference.child(user).child("Email").setValue(Email);
                                            Toast.makeText(RegisterActivity.this, "registration successful", Toast.LENGTH_LONG).show();
                                            username.getEditText().getText().clear();
                                            password.getEditText().getText().clear();
                                            email.getText().clear();
                                            Intent intent = new Intent(RegisterActivity.this, loginActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "username already exists", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    else{
                                        verify.setError("The code does not match with what it was sent");
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
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

                    RequestQueue rQueue = Volley.newRequestQueue(RegisterActivity.this);
                    rQueue.add(request);
                }
            }
        });

        Button sendButton = findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackgroundMail.newBuilder(RegisterActivity.this)
                        .withUsername("confirmstudytool@gmail.com")
                        .withPassword("study_tool10")
                        // .withSenderName("")
                        .withMailTo(email.getText().toString())
                        //.withMailCc("cc-email@gmail.com")
                        //.withMailBcc("bcc-email@gmail.com")
                        .withType(BackgroundMail.TYPE_PLAIN)
                        .withSubject("Confirmation code")
                        .withBody("Your confirmation code is " + randomCode)
                        //.withAttachments(Environment.getExternalStorageDirectory().getPath() + "/test.txt")
                        //.withSendingMessage(R.string.sending_email)
                        .withOnSuccessCallback(new BackgroundMail.OnSendingCallback() {
                            @Override
                            public void onSuccess() {
                                // do some magic
                            }

                            @Override
                            public void onFail(Exception e) {
                                // do some magic
                            }
                        })
                        .send();
                Toast.makeText(RegisterActivity.this, "Mail sent", Toast.LENGTH_LONG).show();
            }

        });

        }
    }


