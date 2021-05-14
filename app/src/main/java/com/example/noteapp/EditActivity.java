package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noteapp.data.DatabaseHelper;
import com.example.noteapp.model.File;

public class EditActivity extends AppCompatActivity {
    EditText title;
    EditText body;
    File file;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        title = (EditText) findViewById(R.id.EditTitle);
        body = (EditText) findViewById(R.id.EditNoteText);
        file = (File) getIntent().getSerializableExtra("file");
        db = new DatabaseHelper(this);

        title.setText(file.getTitle());
        body.setText(file.getBody());

    }

    public void updateNote(View view) {

        String titleText = title.getText().toString();
        String bodyText = body.getText().toString();

        int result = db.updateTexts(new File(file.getID(), titleText, bodyText));

        if (result > 0)
        {
            Toast.makeText(this, "File updated successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), ShowNotes.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "File not updated", Toast.LENGTH_SHORT).show();
        }

        finish();

    }

    public void deleteNote(View view) {

        String titleText = title.getText().toString();
        String bodyText = body.getText().toString();

        int result = db.deleteTexts(new File(file.getID(), titleText, bodyText));

        if (result > 0)
        {
            Toast.makeText(this, "File deleted successfully", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(getApplicationContext(), ShowNotes.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "File not deleted", Toast.LENGTH_SHORT).show();
        }

        finish();


    }
}