package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addNoteBtn;
    RecyclerView recyclerView;
    ImageButton menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNoteBtn = findViewById(R.id.addNewNote);
        recyclerView = findViewById(R.id.recycler_view);
        menuBtn = findViewById(R.id.menu_btn);

        addNoteBtn.setOnClickListener((v)-> startActivity(new Intent(MainActivity.this, NoteDetailsActivity.class)));
        menuBtn.setOnClickListener((v)->showMenu());
        setupRecyclerView();
    }

    void showMenu(){

    }

    void setupRecyclerView(){

    }


}