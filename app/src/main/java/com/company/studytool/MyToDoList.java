package com.company.studytool;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MyToDoList extends AppCompatActivity {
    private NodeViewModel nodeViewModel;
    public static final int ADD_NOTE_CODE = 1;
    public static final int Edit_NOTE_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_to_do_list);
        FloatingActionButton actionButton = findViewById(R.id.add_note);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyToDoList.this, AddNodeActivity.class);
                startActivityForResult(intent, ADD_NOTE_CODE);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final NodeViewAdapter adapter = new NodeViewAdapter();
        recyclerView.setAdapter(adapter);
        nodeViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(NodeViewModel.class);
        nodeViewModel.getListLiveData().observe(this, new Observer<List<Node>>() {
            @Override
            public void onChanged(List<Node> nodes) {
                adapter.submitList(nodes);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                nodeViewModel.delete(adapter.getNodeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MyToDoList.this, "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new NodeViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Node node) {
                Intent intent = new Intent(MyToDoList.this, AddNodeActivity.class);
                intent.putExtra(AddNodeActivity.Extra_ID, node.getId());
                intent.putExtra(AddNodeActivity.Extra_Title, node.getTitle());
                intent.putExtra(AddNodeActivity.Extra_Description, node.getDescription());
                intent.putExtra(AddNodeActivity.Extra_Priority, node.getPriority());
                startActivityForResult(intent, Edit_NOTE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTE_CODE && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddNodeActivity.Extra_Title);
            String description = data.getStringExtra(AddNodeActivity.Extra_Description);
            int priority = data.getIntExtra(AddNodeActivity.Extra_Priority, 1);
            Node node = new Node(title, description, priority);
            nodeViewModel.insert(node);
            Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == Edit_NOTE_CODE && resultCode == RESULT_OK) {
            int id = getIntent().getIntExtra(AddNodeActivity.Extra_ID, -1);
            if (id != -1) {
                Toast.makeText(this, "Note can not be updated", Toast.LENGTH_SHORT).show();
                return;
            } else {
                String title = data.getStringExtra(AddNodeActivity.Extra_Title);
                String description = data.getStringExtra(AddNodeActivity.Extra_Description);
                int priority = data.getIntExtra(AddNodeActivity.Extra_Priority, 1);
                Node node = new Node(title, description, priority);
                node.setId(id);
                nodeViewModel.update(node);
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
            case R.id.save_node:
                nodeViewModel.deleteAll();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}