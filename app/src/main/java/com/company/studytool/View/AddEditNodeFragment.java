package com.company.studytool.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.company.studytool.R;

import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddEditNodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddEditNodeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static final String Extra_Title = "title";
    public static final String Extra_Description = "description";
    public static final String Extra_Priority = "priority";
    public static final String Extra_ID = "ID";

    private EditText title;
    private EditText description;
    private NumberPicker priority;
    private Toolbar toolbar;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddEditNodeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddEditNodeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddEditNodeFragment newInstance(String param1, String param2) {
        AddEditNodeFragment fragment = new AddEditNodeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_add_edit_node, container, false);
        setHasOptionsMenu(true);

        title = root.findViewById(R.id.edit_text_title);
        description = root.findViewById(R.id.edit_text_description);
        priority = root.findViewById(R.id.number_picker_priority);
        toolbar = root.findViewById(R.id.toolbar);
        priority.setMinValue(1);
        priority.setMaxValue(10);

        AppCompatActivity activity = (AppCompatActivity) getActivity();

        activity.setSupportActionBar(toolbar);
        Objects.requireNonNull(activity.getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getActivity().getIntent();
        if (getActivity().getIntent().hasExtra(Extra_ID)) {
            getActivity().setTitle("Edit Note");
            title.setText(intent.getStringExtra(Extra_Title));
            description.setText(intent.getStringExtra(Extra_Description));
            priority.setValue(intent.getIntExtra(Extra_Priority, 1));
        } else {
            getActivity().setTitle("Add Note");
        }
        return root;
    }

    private void saveNote() {

        String titleText = title.getText().toString();
        String descriptionText = description.getText().toString();
        int priorityNumber = priority.getValue();
        if (titleText.trim().isEmpty() || descriptionText.trim().isEmpty()) {
            Toast.makeText(getActivity(), "Please insert a title and description", Toast.LENGTH_LONG).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(Extra_Title, titleText);
        data.putExtra(Extra_Description, descriptionText);
        data.putExtra(Extra_Priority, priorityNumber);
      //  int id = getActivity().getParent().getParentActivityIntent().getIntExtra(Extra_ID, 1);
       int id = NavUtils.getParentActivityIntent(getActivity()).getIntExtra(Extra_ID,1);
        if (id != -1) {
            data.putExtra(Extra_ID, id);
        }
        getActivity();
        getActivity().setResult(Activity.RESULT_OK, data);
        getActivity().finish();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
         menuInflater= getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.add_node_menu, menu);
        super.onCreateOptionsMenu(menu,menuInflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_node:
                saveNote();
                return true;
            case R.id.close_node:
                getActivity().finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}