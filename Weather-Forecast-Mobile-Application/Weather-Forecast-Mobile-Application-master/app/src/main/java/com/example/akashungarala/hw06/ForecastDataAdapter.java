/*
File Name:    ForecastDataAdapter.java
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
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ForecastDataAdapter extends ArrayAdapter<HourlyWeather> {
    List<HourlyWeather> mData;
    Context mContext;
    int mResource;
    DBHelper database;
    TextView tempTextView;
    GlobalData gb;
    DatabaseDataManager ddm;
    public ForecastDataAdapter(Context context, int resource, List<HourlyWeather> objects) {
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

        HourlyWeather hw = mData.get(position);


        TextView maxTempTextView = (TextView) convertView.findViewById(R.id.textView4);
        maxTempTextView.setText(hw.getMinimumTemp()+"°F");

        TextView minTempTextView = (TextView) convertView.findViewById(R.id.textView6);
        minTempTextView.setText(hw.getMaximumTemp() + "°F / ");

        TextView timeTextView = (TextView) convertView.findViewById(R.id.textView5);
        timeTextView.setText(hw.getDay() + "" + hw.getMonth());



        TextView weatherTextView = (TextView) convertView.findViewById(R.id.textView3);
        weatherTextView.setText(hw.getClimateType());

        ImageView bookmarkView = (ImageView)convertView.findViewById(R.id.bookmarkView);
        bookmarkView.setImageResource(R.drawable.bookmark);
        bookmarkView.setVisibility(View.INVISIBLE);

        gb=(GlobalData)this.getContext().getApplicationContext();


        ddm=gb.getDdm();
        ArrayList<Note> notesList=ddm.getNoteTableDAO().getNotesByType(NoteTable.COLUMN_DATE, hw.getDay()+hw.getMonth()+"2016"+gb.getCurrentState()+gb.getCurrentCity());
        if(notesList.size()==0){}else{
            bookmarkView.setVisibility(View.VISIBLE);
        }


        ImageView view = (ImageView) convertView.findViewById(R.id.imageView);
        String url =hw.getIconUrl();
        if(url.equals("")){}else {
            Picasso.with(getContext()).load(url).into(view);
        }

        return convertView;

    }
}
