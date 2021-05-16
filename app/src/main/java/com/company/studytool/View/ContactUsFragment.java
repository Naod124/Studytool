package com.company.studytool.View;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.company.studytool.Model.StudentDetails;
import com.company.studytool.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactUsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactUsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RatingBar ratingBar;
    private Button rateButton;
    private DatabaseReference databaseReference;
    float ratingValue;
    private int ratingCounter = 2;

    public ContactUsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactUsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactUsFragment newInstance(String param1, String param2) {
        ContactUsFragment fragment = new ContactUsFragment();
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
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ratingBar = view.findViewById(R.id.rating_bar);
        rateButton = view.findViewById(R.id.rateButton);
        databaseReference = FirebaseDatabase.getInstance().getReference("Rates/" + StudentDetails.username);
        rateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingCounter--;
                if (ratingCounter > 0) {
                    ratingValue = ratingBar.getRating();
                    if (ratingValue < 2) {
                        try {
                            pushToDB(ratingValue);
                            alertDialog("Just " + ratingValue + " Stars " + "Oops we are really sorry , we would appreciate if you contact support and give us your feed back ");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (ratingValue >= 2 && ratingValue <= 3) {
                        try {
                            pushToDB(ratingValue);
                            alertDialog(ratingValue + " Stars " + "That was fair enough , but we would appreciate if you contact support and give us your feed back ");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    } else if (ratingValue > 3) {
                        try {
                            pushToDB(ratingValue);
                            alertDialog(ratingValue + " Stars " + "Thanks ! , we would appreciate if you contact support and give us your feed back ");
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        ratingBar.setVisibility(View.INVISIBLE);
                        rateButton.setVisibility(View.INVISIBLE);
                        alertDialog("You cannot rate the app more than on time each log in");
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // Inflate the layout for this fragment

        return view;
    }

    private void alertDialog(String message) throws FileNotFoundException {
        AlertDialog alertDialog = new AlertDialog.Builder(Objects.requireNonNull(getActivity()))
                .setMessage(message).setTitle("Thanks for your feedback").setNeutralButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getContext(), "You are great", Toast.LENGTH_LONG).show();
                    }
                }).show();
    }

    public void pushToDB(float ratingValue) {
        databaseReference.push()
                .setValue(ratingValue).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
            }
        });
    }
}