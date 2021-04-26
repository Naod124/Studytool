package com.company.studytool.View;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.company.studytool.Model.StudentDetails;
import com.company.studytool.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link student_list#newInstance} factory method to
 * create an instance of this fragment.
 */
public class student_list extends Fragment {

    ListView list;
    TextView noStudentText;
    ArrayList<String> al = new ArrayList<>();
    int totalStudents = 0;
    ProgressDialog progressDialog;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public student_list() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment student_list.
     */
    // TODO: Rename and change types and number of parameters
    public static student_list newInstance(String param1, String param2) {
        student_list fragment = new student_list();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_student_list, container, false);

        list = (ListView)root.findViewById(R.id.usersList);
        noStudentText = (TextView)root.findViewById(R.id.noUsersText);

        progressDialog = new ProgressDialog(getActivity());
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

        RequestQueue rQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        rQueue.add(request);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StudentDetails.chatWith = al.get(position);
                startActivity(new Intent(getActivity(), privateMessageActivity.class));
            }
        });

        return root;
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
            list.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, al));
        }

        progressDialog.dismiss();
    }
}