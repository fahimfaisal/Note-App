package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.noteapp.data.DatabaseHelper;
import com.example.noteapp.model.File;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowNotes extends AppCompatActivity {

    private  RecyclerView recyclerView;
    private recyclerAdapter.RecyclerViewClickListner listner;
    ArrayList<String> title;
    ArrayList<File> fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);
        recyclerView= findViewById(R.id.recyclerview);
        title = new ArrayList<>();
        DatabaseHelper db = new DatabaseHelper(this);
        fileList = new ArrayList<>();

        fileList = db.fetchAllFiles();
        
        SetAdapter();
    }



    private void SetAdapter()
    {
        setOnClickListener();
        recyclerAdapter adapter = new recyclerAdapter(fileList, listner);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {

        listner = new recyclerAdapter.RecyclerViewClickListner() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                intent.putExtra("file", (Serializable) fileList.get(position));
                startActivity(intent);
                finish();
            }
        };
    }

}