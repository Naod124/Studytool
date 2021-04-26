package com.company.studytool.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.company.studytool.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseFragment extends Fragment  {
    Button moveToDS;
    Button moveToOS;
    Button moveToDC;
    Button moveToDigital;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseFragment newInstance(String param1, String param2) {
        CourseFragment fragment = new CourseFragment();
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
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_course, container, false);

        moveToDS = root.findViewById(R.id.moveToDS);
        moveToDC = root.findViewById(R.id.moveToDC);
        moveToOS = root.findViewById(R.id.moveToOS);
        moveToDigital = root.findViewById(R.id.moveToDigital);
        moveToDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectedCourseActivity.class);
                intent.putExtra("course", "Data Structure");
                startActivity(intent);
            }
        });
        moveToDC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectedCourseActivity.class);
                intent.putExtra("course", "Data Communication");
                startActivity(intent);
            }
        });
        moveToOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectedCourseActivity.class);
                intent.putExtra("course", "Operating System");
                startActivity(intent);
            }
        });
        moveToDigital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectedCourseActivity.class);
                intent.putExtra("course", "Digital System Design");
                startActivity(intent);
            }
        });
        return root;
    }


    public void onClickk(View v) {
        if (v.getId() == moveToDS.getId()) {
            Intent intent = new Intent(getActivity(), SelectedCourseActivity.class);
            intent.putExtra("course", "Data Structure");
            startActivity(intent);
        } else if (v.getId() == moveToDC.getId()) {
            Intent intent = new Intent(getActivity(), SelectedCourseActivity.class);
            intent.putExtra("course", "Data Communication");
            startActivity(intent);
        } else if (v.getId() == moveToOS.getId()) {
            Intent intent = new Intent(getActivity(), SelectedCourseActivity.class);
            intent.putExtra("course", "Operating System");
            startActivity(intent);
        } else if (v.getId() == moveToDigital.getId()) {
            Intent intent = new Intent(getActivity(), SelectedCourseActivity.class);
            intent.putExtra("course", "Digital System Design");
            startActivity(intent);
        }
    }


}