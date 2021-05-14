package com.company.studytool.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.company.studytool.Adapter.ToDoListViewAdapter;
import com.company.studytool.R;
import com.company.studytool.Model.ToDoList;
import com.company.studytool.ViewModel.ToDoListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MyToDoListActivity extends AppCompatActivity {
    private ToDoListViewModel nodeViewModel;
    private Toolbar toolbar;
    public static final int ADD_NOTE_CODE = 1;
    public static final int Edit_NOTE_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_my_to_do_list);
        FloatingActionButton actionButton = findViewById(R.id.add_note);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyToDoListActivity.this, AddEditNodeActivity.class);
                startActivityForResult(intent, ADD_NOTE_CODE);
            }
        });

        toolbar = findViewById(R.id.toolbar_main);
        toolbar.setTitle("To Do List");
        setSupportActionBar(toolbar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final ToDoListViewAdapter adapter = new ToDoListViewAdapter();
        recyclerView.setAdapter(adapter);
        nodeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(ToDoListViewModel.class);
        nodeViewModel.getListLiveData().observe(this, new Observer<List<ToDoList>>() {
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
                Toast.makeText(MyToDoListActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new ToDoListViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ToDoList toDoList) {
                Intent intent = new Intent(MyToDoListActivity.this, AddEditNodeFragment.class);
                intent.putExtra(AddEditNodeFragment.Extra_ID, toDoList.getId());
                intent.putExtra(AddEditNodeFragment.Extra_Title, toDoList.getTitle());
                intent.putExtra(AddEditNodeFragment.Extra_Description, toDoList.getDescription());
                intent.putExtra(AddEditNodeFragment.Extra_Priority, toDoList.getPriority());
                startActivityForResult(intent, Edit_NOTE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditNodeFragment.Extra_Title);
            String description = data.getStringExtra(AddEditNodeFragment.Extra_Description);
            int priority = data.getIntExtra(AddEditNodeFragment.Extra_Priority, 1);
            ToDoList toDoList = new ToDoList(title, description, priority);
            nodeViewModel.insert(toDoList);
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == Edit_NOTE_CODE && resultCode == RESULT_OK) {
            Intent intent = getIntent();
            int id = intent.getIntExtra(AddEditNodeActivity.Extra_ID, -1);
            if (intent.hasExtra(AddEditNodeActivity.Extra_ID)) {
                Toast.makeText(this, "id" + id, Toast.LENGTH_LONG).show();
            }
            if (id == -1) {
                Toast.makeText(this, "Note can not be updated" + id, Toast.LENGTH_SHORT).show();
                return;
            } else {
                String title = data.getStringExtra(AddEditNodeActivity.Extra_Title);
                String description = data.getStringExtra(AddEditNodeActivity.Extra_Description);
                int priority = data.getIntExtra(AddEditNodeActivity.Extra_Priority, 1);
                ToDoList toDoList = new ToDoList(title, description, priority);
                toDoList.setId(id);
                nodeViewModel.update(toDoList);
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
            }
        } else Toast.makeText(this, "Note Canceled", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu_notes, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.deleteAll:
                nodeViewModel.deleteAll();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}