/*
File Name:    AddNotesActivity.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNotesActivity extends AppCompatActivity {
    GlobalData gb;
    DBHelper database;
    DatabaseDataManager ddm;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sun_cloud);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Add Notes Activity");
        gb=(GlobalData)getApplication();
        ddm = gb.getDdm();
        et = (EditText)findViewById(R.id.editText7);
        Button bt4 = (Button)findViewById(R.id.button4);
        bt4.setOnClickListener(new View.OnClickListener() {
                                   public void onClick(View v) {
                                       if(et.getText().length()>0 && et.getText().length()<=30) {
                                           Note temp = new Note();
                                           temp.setSavedDate(gb.getToSaveDate());
                                           temp.setSavedContent(et.getText().toString());
                                           ddm.getNoteTableDAO().saveNote(temp);
                                           Intent it = new Intent(getBaseContext(), CityDataActivity.class);
                                           it.putExtra("isFromAddNoteActivity", "yes");
                                           startActivity(it);
                                           Toast.makeText(AddNotesActivity.this, "Note Saved Succesfully", Toast.LENGTH_SHORT).show();
                                           finish();
                                       }
                                       else if(et.getText().length()>30)
                                       {
                                           Toast.makeText(AddNotesActivity.this,"Please Enter note between 1 and 30 characters",Toast.LENGTH_SHORT).show();
                                           et.setText("");
                                       }
                                       else
                                       {
                                           Toast.makeText(AddNotesActivity.this,"Please Enter Data",Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               }
        );
    }
}