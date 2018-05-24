/*
File Name:    CityData2Activity.java
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
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CityData2Activity extends AppCompatActivity {
    FragmentTabHost mTabHost = null;;
    List<Fragment> list;
    GlobalData gd;
    ListView lv3;
    HourlyDataFragment hourlyDataFragment;
    ArrayList<HourlyWeather> bd;
    DatabaseDataManager ddm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_data2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        /*
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);

        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("0").setIndicator("HOURLY DATA"), HourlyDataFragment.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("1").setIndicator("FORECAST"), ForecastDataFragment.class, null);




        RequestParams rp1 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/hourly/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
        new GetDataWithParams().execute(rp1);

        */

        Button hourlyBT = (Button)findViewById(R.id.HourlyBT);
        hourlyBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RequestParams rp1 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/hourly/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
                new GetDataWithParams().execute(rp1);
            }
        });

        Button forecastBT = (Button)findViewById(R.id.forecastBT);
        forecastBT.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("start forecast","@");
                RequestParams rp2 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/forecast10day/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
                new GetForecastDataWithParams().execute(rp2);
            }
        });





        lv3=(ListView)findViewById(R.id.listView3);
        lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), AddNotesActivity.class);
                GlobalData gd = (GlobalData) getApplication();
                HourlyWeather hw = bd.get(position);
                gd.setToSaveDate(hw.getDay() + hw.getMonth() + hw.getYear() + gd.getCurrentState() + gd.getCurrentCity());
                Log.v("gd!!!", gd.getToSaveDate());
                startActivity(i);
                finish();

            }
        });

        lv3.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                HourlyWeather hw = (HourlyWeather) lv3.getItemAtPosition(position);


                ddm = gd.getDdm();
                ddm.getNoteTableDAO().deleteNotesByType(NoteTable.COLUMN_DATE, hw.getDay()+hw.getMonth()+"2016"+gd.getCurrentState()+gd.getCurrentCity());
                RequestParams rp2 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/forecast10day/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
                new GetForecastDataWithParams().execute(rp2);


                return true;
            }
        });


        Intent i=getIntent();
        gd = (GlobalData)getApplication();

        if(i.getExtras().getString("isFromAddNoteActivity")==null){
            gd.setCurrentState(i.getExtras().getString("state"));
            gd.setCurrentCity(i.getExtras().getString("city"));
            RequestParams rp1 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/hourly/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
            new GetDataWithParams().execute(rp1);
        }else{


            RequestParams rp2 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/forecast10day/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
            new GetForecastDataWithParams().execute(rp2);

        }



        //Log.v("city intent", i.getExtras().getString("state") + "  " + i.getExtras().getString("city"));

        TextView tx= (TextView)findViewById(R.id.textView2);
        tx.setText(gd.getCurrentState() + "  " + gd.getCurrentCity());



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
            Log.v("@@@bd", "@" + bd.toString());
            HourlyDataAdapter hourlyWeatherAdapter = new HourlyDataAdapter(getBaseContext(), R.layout.image_text_layout, bd);
            hourlyWeatherAdapter.setNotifyOnChange(true);
            lv3.setAdapter(hourlyWeatherAdapter);


/*

            FragmentManager fragmentManager = getFragmentManager ();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            HourlyDataFragment hourlyDataFragment = new HourlyDataFragment ();
            fragmentTransaction.add(R.id.test, hourlyDataFragment);
            fragmentTransaction.addToBackStack("myFrag");
            fragmentTransaction.commit();
            gd.setAllHourslyWeather(bd);*/

            //setupAdapterData(bd);
            /*


             */


        }

    }
    /*
        public void setupAdapterData(ArrayList<HourlyWeather> hourlyWeatherList) {


            hourlyWeatherAdapter = new HourlyDataAdapter(this.getActivity(),R.layout.image_text_layout, hourlyWeatherList);
            hourlyWeatherAdapter.setNotifyOnChange(true);
            lv.setAdapter(hourlyWeatherAdapter);
        }*/
    class GetForecastDataWithParams extends AsyncTask<RequestParams,Integer,ArrayList<HourlyWeather>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<HourlyWeather> doInBackground(RequestParams... params) {
            Log.v("doing forecast","@");
            try {
                HttpURLConnection con = params[0].setupConnection();
                con.setRequestMethod("GET");
                InputStream in = con.getInputStream();

                return InputStreamConverterUtil.FullParser.getForecastData(in);

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
        protected void onPostExecute(ArrayList<HourlyWeather> result) {
            super.onPostExecute(result);

            bd=result;



            ForecastDataAdapter forecastWeatherAdapter = new ForecastDataAdapter(getBaseContext(), R.layout.image_text_layout, bd);
            forecastWeatherAdapter.setNotifyOnChange(true);
            lv3.setAdapter(forecastWeatherAdapter);

        }

    }




}