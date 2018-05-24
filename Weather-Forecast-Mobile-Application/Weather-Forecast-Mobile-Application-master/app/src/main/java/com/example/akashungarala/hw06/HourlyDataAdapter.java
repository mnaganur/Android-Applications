/*
File Name:    HourlyDataAdapter.java
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

import java.util.List;


public class HourlyDataAdapter extends ArrayAdapter<HourlyWeather> {
    List<HourlyWeather> mData;
    Context mContext;
    int mResource;
    DBHelper database;
    TextView tempTextView;
    public HourlyDataAdapter(Context context, int resource, List<HourlyWeather> objects) {
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

        final HourlyWeather hw = mData.get(position);


        TextView tempTextView = (TextView) convertView.findViewById(R.id.textView4);
        tempTextView.setText(hw.getTemperature()+"°F");

        TextView timeTextView = (TextView) convertView.findViewById(R.id.textView5);
        timeTextView.setText(hw.getTime());

        TextView weatherTextView = (TextView) convertView.findViewById(R.id.textView3);
        weatherTextView.setText(hw.getClimateType());

        ImageView view = (ImageView) convertView.findViewById(R.id.imageView);
        String url =hw.getIconUrl();
        if(url.equals("")){}else {
            Picasso.with(getContext()).load(url).into(view);
        }
        return convertView;

    }
}