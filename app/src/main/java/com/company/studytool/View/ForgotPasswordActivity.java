package com.company.studytool.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.company.studytool.R;
import com.creativityapps.gmailbackgroundlibrary.BackgroundMail;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Random;

public class ForgotPasswordActivity extends AppCompatActivity {
    Random rand = new Random();

    int randomCode = rand.nextInt(999999);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText emailText = findViewById(R.id.emailSend);

                BackgroundMail.newBuilder(ForgotPasswordActivity.this)
                        .withUsername("confirmstudytool@gmail.com")
                        .withPassword("study_tool10")
                       // .withSenderName("")
                        .withMailTo(emailText.getText().toString())
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
                                new AlertDialog.Builder(ForgotPasswordActivity.this)
                                        .setTitle("Mail sent")
                                        .setMessage("Check your mail for the code")
                                        .setPositiveButton(android.R.string.ok, null)
                                        .show();

                            }

                            @Override
                            public void onFail(Exception e) {
                                // do some magic
                                new AlertDialog.Builder(ForgotPasswordActivity.this)
                                        .setTitle("Mail was not sent")
                                        .setMessage("verify if you typed correct email")
                                        .setPositiveButton(android.R.string.ok, null)
                                        .show();
                            }
                        })
                        .send();
                    Toast.makeText(ForgotPasswordActivity.this,"Mail sent", Toast.LENGTH_LONG).show();


                    Toast.makeText(ForgotPasswordActivity.this,"Mail sent", Toast.LENGTH_LONG).show();
           }
        });
        Button confirm = findViewById(R.id.Confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

   public void reset(){

       TextInputEditText resetCode = findViewById(R.id.verifyCodee);
       TextInputEditText newPass = findViewById(R.id.newPass);
       TextInputEditText repeatPass = findViewById(R.id.repeatPass);       TextInputEditText userName = findViewById(R.id.usernamn);
       TextInputEditText email = findViewById(R.id.emailSend);

       int randomNumber = Integer.parseInt(resetCode.getText().toString());
       if (randomNumber==randomCode){
           if (newPass.getText().toString().equalsIgnoreCase(repeatPass.getText().toString())){
                    HashMap<String, Object> result = new HashMap<>();
                            result.put("Password", newPass.getText().toString());
                              FirebaseDatabase.getInstance().getReference().child("Users").child(userName.getText().toString()).updateChildren(result);
                             Toast.makeText(ForgotPasswordActivity.this, "Password is updated",Toast.LENGTH_LONG).show();
                             startActivity(new Intent(ForgotPasswordActivity.this, loginActivity.class));
               new AlertDialog.Builder(ForgotPasswordActivity.this)
                       .setTitle("Password is updated ")
                       .setMessage("You can log in with your new password")
                       .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               startActivity(new Intent(ForgotPasswordActivity.this, loginActivity.class));
                           }
                       })
                       .show();
           }
           else {
               repeatPass.setError("The password you entered does not match");
           }
       }
       else{
           resetCode.setError("The code does not match with the one sent to your email");
       }
   }
    }
