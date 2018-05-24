/*
File Name:    DatabaseOpenHelper.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    static final String DB_NAME = "mainDB.db";
    static final int DB_VERSION=1;

    public DatabaseOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //NoteTable.onCreate(this.getWritableDatabase());
    }




    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        NoteTable.onUpgrade(db, oldVersion,newVersion);
    }
}