/*
File Name:    NotesAdapter.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class NotesAdapter extends ArrayAdapter<Note> {
    List<Note> mData;
    Context mContext;
    int mResource;
    DBHelper database;
    TextView tempTextView;
    public NotesAdapter(Context context, int resource, List<Note> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mData = objects;
        this.mResource = resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(mResource,parent,false);
        }
        final Note note = mData.get(position);
        TextView noteTextView = (TextView) convertView.findViewById(R.id.cityTextView);
        noteTextView.setText(note.getSavedContent());
        tempTextView = (TextView) convertView.findViewById(R.id.stateTextView);
        String m= note.getSavedDate();
        tempTextView.setText(m.substring(0,2)+" "+m.substring(2,5));
        return convertView;
    }
}