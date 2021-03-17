package com.company.studytool.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.company.studytool.R;
import com.company.studytool.Model.StudentDetails;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class privateMessageActivity extends AppCompatActivity {

    LinearLayout layout;

    ScrollView scrollView;
    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_private_message);

        layout = (LinearLayout)findViewById(R.id.layout1);
        ImageView sendButton = (ImageView)findViewById(R.id.sendButton);
        EditText messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);

        Firebase.setAndroidContext(this);
        Firebase reference1 = new Firebase("https://studytool-304217-default-rtdb.firebaseio.com/Messages/" + StudentDetails.username + "_" + StudentDetails.chatWith +  strDate);
        Firebase reference2 = new Firebase("https://studytool-304217-default-rtdb.firebaseio.com/Messages/" + StudentDetails.chatWith + "_" + StudentDetails.username   + strDate);
        Firebase reference3 = new Firebase("https://studytool-304217-default-rtdb.firebaseio.com/TimeStamp/");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();
                strDate = sdfDate.format(now);

                if(!messageText.equals("")){
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("Messages", messageText);
                    map.put("Users", StudentDetails.username);
                    map.put("TimeStamp",strDate);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);
                    reference3.push().setValue(map);
                }
            }
        });

        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map map = dataSnapshot.getValue(Map.class);
                String message = map.get("Messages").toString();
                String userName = map.get("Users").toString();
                String timestamp = map.get("TimeStamp").toString();

                if(userName.equals(StudentDetails.username)){
                    addMessageBox("Me:-                                                   "+timestamp+"\n\n" + message, 1);
                    messageArea.getText().clear();
                }
                else{
                    addMessageBox(StudentDetails.chatWith + ":-                                        "+timestamp+"\n\n" + message, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void addMessageBox(String message, int type){
        TextView textView = new TextView(privateMessageActivity.this);
        textView.setText(message);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 0, 10);
        textView.setLayoutParams(lp);

        if(type == 1) {
            textView.setBackgroundResource(R.drawable.rounded_corner1);
        }
        else{
            textView.setBackgroundResource(R.drawable.rounded_corner2);
        }

        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}