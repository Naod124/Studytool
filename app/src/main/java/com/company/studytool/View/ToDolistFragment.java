package com.company.studytool.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.company.studytool.Adapter.ToDoListViewAdapter;
import com.company.studytool.Model.ToDoList;
import com.company.studytool.R;
import com.company.studytool.ViewModel.ToDoListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDolistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDolistFragment extends Fragment {
    private ToDoListViewModel nodeViewModel;
    private Toolbar toolbar;
    public static final int ADD_NOTE_CODE = 1;
    public static final int Edit_NOTE_CODE = 2;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToDolistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToDolist.
     */
    // TODO: Rename and change types and number of parameters
    public static ToDolistFragment newInstance(String param1, String param2) {
        ToDolistFragment fragment = new ToDolistFragment();
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
        View root = inflater.inflate(R.layout.fragment_to_dolist, container, false);
        setHasOptionsMenu(true);
        FloatingActionButton actionButton = root.findViewById(R.id.add_note);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddEditNodeActivity.class);
               // startActivity(new Intent(getActivity(), AddEditNodeActivity.class));
                startActivityForResult(intent, ADD_NOTE_CODE);
  /*              getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragmentsHolder, new AddEditNodeFragment())
                        .commit();
*/

            }
        });

        toolbar = root.findViewById(R.id.toolbar_main);
        toolbar.setTitle("To Do List");
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        RecyclerView recyclerView = root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        final ToDoListViewAdapter adapter = new ToDoListViewAdapter();
        recyclerView.setAdapter(adapter);
        nodeViewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(Objects.requireNonNull(getActivity()).getApplication())).get(ToDoListViewModel.class);
        nodeViewModel.getListLiveData().observe(getActivity(), new Observer<List<ToDoList>>() {
            @Override
            public void onChanged(List<ToDoList> toDoLists) {
                adapter.submitList(toDoLists);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                nodeViewModel.delete(adapter.getNodeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new ToDoListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ToDoList toDoList) {
                Intent intent = new Intent(getActivity(), AddEditNodeActivity.class);
                intent.putExtra(AddEditNodeActivity.Extra_ID, toDoList.getId());
                intent.putExtra(AddEditNodeActivity.Extra_Title, toDoList.getTitle());
                intent.putExtra(AddEditNodeActivity.Extra_Description, toDoList.getDescription());
                intent.putExtra(AddEditNodeActivity.Extra_Priority, toDoList.getPriority());
                startActivityForResult(intent, Edit_NOTE_CODE);
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_CODE && resultCode == getActivity().RESULT_OK) {
            String title = data.getStringExtra(AddEditNodeActivity.Extra_Title);
            String description = data.getStringExtra(AddEditNodeActivity.Extra_Description);
            int priority = data.getIntExtra(AddEditNodeActivity.Extra_Priority, 1);
            ToDoList toDoList = new ToDoList(title, description, priority);
            nodeViewModel.insert(toDoList);
            Toast.makeText(getActivity(), "Note Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == Edit_NOTE_CODE && resultCode == getActivity().RESULT_OK) {
            Intent intent = getActivity().getIntent();
            int id = intent.getIntExtra(AddEditNodeActivity.Extra_ID, -1);
            if (intent.hasExtra(AddEditNodeActivity.Extra_ID)) {
                Toast.makeText(getActivity(), "id" + id, Toast.LENGTH_LONG).show();
            }
            if (id == -1) {
                Toast.makeText(getActivity(), "Note can not be updated" + id, Toast.LENGTH_SHORT).show();
                return;
            } else {
                String title = data.getStringExtra(AddEditNodeActivity.Extra_Title);
                String description = data.getStringExtra(AddEditNodeActivity.Extra_Description);
                int priority = data.getIntExtra(AddEditNodeActivity.Extra_Priority, 1);
                ToDoList toDoList = new ToDoList(title, description, priority);
                toDoList.setId(id);
                nodeViewModel.update(toDoList);
                Toast.makeText(getActivity(), "Note updated", Toast.LENGTH_SHORT).show();
            }
        } else Toast.makeText(getActivity(), "Note Canceled", Toast.LENGTH_SHORT).show();

    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = Objects.requireNonNull(getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_notes, menu);
        return true;
    }*/

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.deleteAll:
                nodeViewModel.deleteAll();
                Toast.makeText(getActivity(), "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}