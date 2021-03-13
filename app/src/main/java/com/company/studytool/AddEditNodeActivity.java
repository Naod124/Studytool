package com.company.studytool;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.util.Objects;

public class AddEditNodeActivity extends AppCompatActivity {
    public static final String Extra_Title = "title";
    public static final String Extra_Description = "description";
    public static final String Extra_Priority = "priority";
    public static final String Extra_ID = "ID";

    private EditText title;
    private EditText description;
    private NumberPicker priority;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_activity);
        title = findViewById(R.id.edit_text_title);
        description = findViewById(R.id.edit_text_description);
        priority = findViewById(R.id.number_picker_priority);
        toolbar = findViewById(R.id.toolbar);
        priority.setMinValue(1);
        priority.setMaxValue(10);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent = getIntent();
        if (intent.hasExtra(Extra_ID)) {
            setTitle("Edit Note");
            title.setText(intent.getStringExtra(Extra_Title));
            description.setText(intent.getStringExtra(Extra_Description));
            priority.setValue(intent.getIntExtra(Extra_Priority, 1));
        } else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {

        String titleText = title.getText().toString();
        String descriptionText = description.getText().toString();
        int priorityNumber = priority.getValue();
        if (titleText.trim().isEmpty() || descriptionText.trim().isEmpty()) {
            Toast.makeText(this, "Please insert a title and descriotion", Toast.LENGTH_LONG).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra(Extra_Title, titleText);
        data.putExtra(Extra_Description, descriptionText);
        data.putExtra(Extra_Priority, priorityNumber);
        int id = getParentActivityIntent().getIntExtra(Extra_ID, 1);
        if (id != -1) {
            data.putExtra(Extra_ID, id);
        }
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_node_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_node:
                saveNote();
                return true;
            case R.id.close_node:
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}