package com.company.studytool.View;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.studytool.R;
import com.firebase.client.Firebase;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddItemsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddItemsFragment extends Fragment {

    private FirebaseDatabase database;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddItemsFragment() {
        // Required empty public constructor
        addItems();
    }

    public static AddItemsFragment newInstance(String param1, String param2) {
        AddItemsFragment fragment = new AddItemsFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);
        //FirebaseDatabase.getInstance().getReference().child("Items").child("Books").child("Operating Systems book").child("Author").setValue("Paul Deitel");

        return view;
    }

    public void addItems(){
        HashMap<String, Object> OSbook1 = new HashMap<>();
        OSbook1.put("Name", "Fundamentals of Operating systems");
        OSbook1.put("Author", "Paul Deitel");
        OSbook1.put("Price", 157);
        OSbook1.put("Edition", 2);

        HashMap<String, Object> OSbook2 = new HashMap<>();
        OSbook2.put("Name", "Practical Linux Operating System");
        OSbook2.put("Author", "Eric Johnson, Marta Kleinberg, Wu Quang");
        OSbook2.put("Price", 283);
        OSbook2.put("Edition", 10);

        HashMap<String, Object> books = new HashMap<>();
        books.put("Book1", OSbook1);
        books.put("Book2", OSbook2);

        FirebaseDatabase.getInstance().getReference().child("Books").updateChildren(books);
    }
}