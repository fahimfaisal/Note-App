package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void createNote(View view) {

        Intent createIntent = new Intent(this, CreateNote.class);

        startActivity(createIntent);

    }

    public void showNote(View view) {

        Intent showIntentIntent = new Intent(this, ShowNotes.class);

        startActivity(showIntentIntent);

    }

}