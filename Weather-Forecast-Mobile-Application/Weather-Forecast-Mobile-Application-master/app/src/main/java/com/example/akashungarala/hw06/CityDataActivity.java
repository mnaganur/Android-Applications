/*
File Name:    CityDataActivity.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.app.Fragment;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CityDataActivity extends AppCompatActivity {
    FragmentTabHost mTabHost = null;;
    List<Fragment> list;
    GlobalData gd;
    ListView lv3;
    HourlyDataFragment hourlyDataFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragments_tabs);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sun_cloud);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("CityData Activity");
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);



        Intent i=getIntent();
        gd = (GlobalData)getApplication();





        if(i.getExtras().getString("isFromAddNoteActivity")==null){
            gd.setCurrentState(i.getExtras().getString("state"));
            gd.setCurrentCity(i.getExtras().getString("city"));
            mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator("HOURLY DATA"), HourlyDataFragment.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("FORECAST"), ForecastDataFragment.class, null);
        }else{
            mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("HOURLY DATA"), HourlyDataFragment.class, null);
            mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator("FORECAST"), ForecastDataFragment.class, null);

        }



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

                return InputStreamConverterUtil.FullParser.getAllHourlyData(in);

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
        protected void onPostExecute(ArrayList<HourlyWeather> bd) {
            super.onPostExecute(bd);


            HourlyDataAdapter hourlyWeatherAdapter = new HourlyDataAdapter(getBaseContext(), R.layout.image_text_layout, bd);
            hourlyWeatherAdapter.setNotifyOnChange(true);
            lv3.setAdapter(hourlyWeatherAdapter);

        }

    }



}