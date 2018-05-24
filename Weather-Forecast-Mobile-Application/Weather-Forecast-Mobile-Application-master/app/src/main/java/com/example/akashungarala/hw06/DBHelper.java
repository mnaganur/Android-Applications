/*
File Name:    DBHelper.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDBName.db";

    public static final String CONTACTS_TABLE_CITIES = "cities";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_CITY = "city";
    public static final String CONTACTS_COLUMN_STATE = "state";

    public static final String CONTACTS_TABLE_NOTES = "notes";
    public static final String CONTACTS_COLUMN_DATE = "date";
    public static final String CONTACTS_COLUMN_NOTE = "note";


    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table cities " +
                        "(id integer primary key, city text, state text)"
        );
        db.execSQL(
                "create table notes " +
                        "(id integer primary key, date text,note text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertToCitiesTable  (String city, String state)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("city", city);
        contentValues.put("state", state);

        db.insert("cities", null, contentValues);
        return true;
    }

    /*public long getID()
    {
        //return _id;
    }*/

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRowsForCitiesTable(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_CITIES);
        return numRows;
    }

    public Cursor queryContact(String type, String t){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where "+type+"="+"'"+t+"'", null );
        return res;
    }

    public void deleteFromTableBasedOnType(String type, String t, String table){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+table+" where "+type+"="+"'"+t+"'", null );
        res.moveToFirst();
        deleteFromTable(res.getInt(res.getColumnIndex("id")),table);

    }


    public boolean updateContact (Integer id, String name, String bm)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("bookmark", bm);

        db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
        Log.v("update", id + " " + contentValues.toString());
        return true;
    }

    public Integer deleteFromTable (Integer id,String table)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(table,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<SavedCityState> getAllSavedCityState()
    {
        ArrayList<SavedCityState> array_list = new ArrayList<SavedCityState>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from cities", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(
                    new SavedCityState(res.getString(res.getColumnIndex("city")), res.getString(res.getColumnIndex("state")))
            );
            res.moveToNext();
        }
        return array_list;
    }

    public void deleteAllRecords(String table)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+table, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            deleteFromTable(res.getInt(res.getColumnIndex("id")),"cities");
            res.moveToNext();
        }

    }

}
