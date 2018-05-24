/*
File Name:    NoteTable.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class NoteTable {
    static final String TABLENAME = "notetable";
    static final String COLUMN_ID = "_id";
    static final String COLUMN_DATE = "date";
    static final String COLUMN_CONTENT = "content";

    static public void onCreate(SQLiteDatabase db) {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE " + TABLENAME + "(");
        sb.append(COLUMN_ID + " integer primary key autoincrement, ");
        sb.append(COLUMN_DATE + " text not null, ");
        sb.append(COLUMN_CONTENT + " text not null);");

        try {
            db.execSQL(sb.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    static public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXSITS"+TABLENAME);
        NoteTable.onCreate(db);
    }
}
