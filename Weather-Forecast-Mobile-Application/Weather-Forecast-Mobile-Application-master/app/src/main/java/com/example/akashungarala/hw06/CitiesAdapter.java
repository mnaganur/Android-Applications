/*
File Name:    CitiesAdapter.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CitiesAdapter extends ArrayAdapter<SavedCityState> {
    List<SavedCityState> mData;
    Context mContext;
    int mResource;
    DBHelper database;
    TextView tempTextView;
    public CitiesAdapter(Context context, int resource, List<SavedCityState> objects) {
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
        final SavedCityState scs = mData.get(position);
        TextView cityTextView = (TextView) convertView.findViewById(R.id.cityTextView);
        cityTextView.setText(scs.getCity()+","+scs.getState());
        tempTextView = (TextView) convertView.findViewById(R.id.stateTextView);
        tempTextView.setText(scs.getTemp());
        return convertView;
    }
    class GetDataWithParams extends AsyncTask<RequestParams,Integer,ArrayList<HourlyWeather>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected ArrayList<HourlyWeather> doInBackground(RequestParams... params) {
            try {
                HttpURLConnection con = params[0].setupConnection();
                con.setRequestMethod("GET");
                InputStream in = con.getInputStream();
                return InputStreamConverterUtil.FullParser.getCurrentTemp(in);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(final ArrayList<HourlyWeather> resultList) {
            super.onPostExecute(resultList);
            HourlyWeather result = (HourlyWeather)resultList.get(0);
            String currentTemp = result.getTemperature();
            tempTextView.setText(currentTemp);
        }
    }
}
