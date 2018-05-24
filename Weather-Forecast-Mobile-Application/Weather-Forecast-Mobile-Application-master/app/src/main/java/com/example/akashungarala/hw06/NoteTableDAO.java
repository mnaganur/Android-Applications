/*
File Name:    NoteTableDAO.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class NoteTableDAO {
    private SQLiteDatabase db;
    public NoteTableDAO(SQLiteDatabase db){
        this.db=db;
        NoteTable.onCreate(db);
    }
    public long saveNote(Note note){
        ContentValues values=new ContentValues();
        values.put(NoteTable.COLUMN_DATE, note.getSavedDate());
        values.put(NoteTable.COLUMN_CONTENT, note.getSavedContent());
        return db.insert(NoteTable.TABLENAME, null, values);
    }
    public boolean deleteNoteById(Integer id){
        return db.delete(NoteTable.TABLENAME,
                "_id = ? ",
                new String[] { Integer.toString(id) })>0;
    }
    public void deleteNotesByType(String type,String t){
        Cursor c =  db.rawQuery( "select * from "+NoteTable.TABLENAME+" where "+type+"="+"'"+t+"'", null );
        c.moveToFirst();
        if(c!=null&&c.moveToFirst()){
            do{
                deleteNoteById(c.getInt(c.getColumnIndex("_id")));
            }while(c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
    }
    public Note getNoteById(long id){
        Note note=null;
        Cursor c = db.query(true,NoteTable.TABLENAME,
                new String[]{NoteTable.COLUMN_ID, NoteTable.COLUMN_DATE, NoteTable.COLUMN_CONTENT},
                NoteTable.COLUMN_ID+"=?",
                new String[]{id+""},null,null,null,null,null);
        if(c!=null&&c.moveToFirst()){
            note = buildNoteFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }
        }
        return note;
    }
    public ArrayList<Note> getNotesByType(String type,String t){
        ArrayList<Note> notes = new ArrayList<Note>();
        Cursor c =  db.rawQuery("select * from " + NoteTable.TABLENAME + " where " + type + "=" + "'" + t + "'", null);
        c.moveToFirst();
        while(c.isAfterLast() == false){
            notes.add(getNoteById(c.getInt(c.getColumnIndex("_id"))));
            c.moveToNext();
        }
        if(!c.isClosed()){
            c.close();
        }
        return notes;
    }
    public ArrayList<Note> getAll(){
        ArrayList<Note> notes = new ArrayList<Note>();
        Cursor c = db.query(NoteTable.TABLENAME,
                new String[]{NoteTable.COLUMN_ID, NoteTable.COLUMN_DATE, NoteTable.COLUMN_CONTENT},
                null,null,null,null,null);
        if(c!=null&&c.moveToFirst()){
            do{
                Note note = buildNoteFromCursor(c);
                if(note!=null){
                    notes.add(note);
                }
            }while(c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return notes;
    }
    private Note buildNoteFromCursor(Cursor c){
        Note note = null;
        if(c != null){
            note = new Note();
            note.set_id(c.getLong(0));
            note.setSavedDate(c.getString(1));
            note.setSavedContent(c.getString(2));
        }
        return note;
    }
}