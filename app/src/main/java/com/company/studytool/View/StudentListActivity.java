package com.company.studytool.View;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.company.studytool.R;
import com.company.studytool.Model.StudentDetails;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class StudentListActivity extends AppCompatActivity {

    ListView list;
    TextView noStudentText;
    ArrayList<String> al = new ArrayList<>();
    int totalStudents = 0;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        list = (ListView)findViewById(R.id.usersList);
        noStudentText = (TextView)findViewById(R.id.noUsersText);

        progressDialog = new ProgressDialog(StudentListActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        String url = "https://studytool-304217-default-rtdb.firebaseio.com/Users.json";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String s) {
                doOnSuccess(s);
            }
        },new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                System.out.println("" + volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(StudentListActivity.this);
        rQueue.add(request);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentDetails.chatWith = al.get(position);
                startActivity(new Intent(StudentListActivity.this, privateMessageActivity.class));
            }
        });
    }

    public void doOnSuccess(String s){
        try {
            JSONObject obj = new JSONObject(s);

            Iterator i = obj.keys();
            String key = "";

            if (i.hasNext()) {
                do {
                    key = i.next().toString();

                    if (!key.equals(StudentDetails.username)) {
                        al.add(key);
                    }

                    totalStudents++;
                } while (i.hasNext());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(totalStudents <=1){
            noStudentText.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }
        else{
            noStudentText.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al));
        }

        progressDialog.dismiss();
    }
}