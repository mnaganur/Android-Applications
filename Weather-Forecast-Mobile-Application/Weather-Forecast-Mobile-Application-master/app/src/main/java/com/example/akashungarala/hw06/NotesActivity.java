/*
File Name:    NotesActivity.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

public class NotesActivity extends AppCompatActivity {
    GlobalData gd;
    DatabaseDataManager ddm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sun_cloud);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Notes Activity");

        gd = (GlobalData)getApplication();
        ddm=gd.getDdm();
        ArrayList<Note> notesList=ddm.getNoteTableDAO().getAll();

        ListView lv = (ListView)findViewById(R.id.listView4);

        NotesAdapter notesAdapter = new NotesAdapter(this,R.layout.two_textview_layout, notesList);
        notesAdapter.setNotifyOnChange(true);
        lv.setAdapter(notesAdapter);

    }

}