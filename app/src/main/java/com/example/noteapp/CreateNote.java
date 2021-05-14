package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapp.data.DatabaseHelper;
import com.example.noteapp.model.File;

public class CreateNote extends AppCompatActivity {
        DatabaseHelper db;
        EditText title;
        EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        title = (EditText) findViewById(R.id.Title);
        body = (EditText) findViewById(R.id.NoteText);
        db = new DatabaseHelper(this);
    }

    public void saveNote(View view) {

        String titleText = title.getText().toString();
        String bodyText = body.getText().toString();

        long result = db.insertFile(new File(titleText,bodyText));

        if (result > 0)
        {
            Toast.makeText(this, "File saved successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), ShowNotes.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast.makeText(this, "File not saved", Toast.LENGTH_SHORT).show();
        }

    }
}