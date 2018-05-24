/*
File Name:    DatabaseDataManager.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseDataManager {
    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NoteTableDAO noteTableDAO;

    public DatabaseDataManager(Context mContext){
        this.mContext=mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);

        db=dbOpenHelper.getWritableDatabase();
        noteTableDAO=new NoteTableDAO(db);

    }

    public void close(){
        if(db!=null){
            db.close();
        }
    }

    public ArrayList<String> getAllTableNames()
    {
        ArrayList<String> allTableNames = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        if (c.moveToFirst()) {
            while (!c.isAfterLast()) {
                allTableNames.add(c.getString(c.getColumnIndex("name")));
                c.moveToNext();
            }
        }
        return allTableNames;
    }






    public NoteTableDAO getNoteTableDAO(){
        return this.noteTableDAO;
    }
    /*
    public long saveNote(Note note){
        return this.noteDAO.save(note);
    }

    public boolean updateNote(Note note){
        return this.noteDAO.update(note);
    }

    public boolean deleteNote(Note note){
        return this.noteDAO.delete(note);
    }

    public Note getNote(long id){
        return this.noteDAO.get(id);
    }

    public List<Note> getAllNotes(){
        return this.noteDAO.getAll();
    }
    */
}
