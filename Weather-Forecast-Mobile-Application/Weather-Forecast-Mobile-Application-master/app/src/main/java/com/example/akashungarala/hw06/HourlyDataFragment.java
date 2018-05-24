/*
File Name:    HourlyDataFragment.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;


public class HourlyDataFragment extends ListFragment {

    GlobalData gd;
    ListView lv;
    HourlyDataAdapter hourlyWeatherAdapter;
    ArrayList<HourlyWeather> hw;





    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.d("mark", "onCreate()--------->hourly Fragment");




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO Auto-generated method stub

        View view = inflater.inflate(R.layout.hourly_data_fragment, container, false);


        Log.d("mark", "onCreateView()--------->hourly Fragment");
        gd = (GlobalData) getActivity().getApplication();
        RequestParams rp1 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/hourly/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
        new GetDataWithParams().execute(rp1);


        return view;
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.d("mark", "onPause()--------->hourly Fragment");
    }

    public void onListItemClick(ListView l, View v, int pos, long id)
    {

        Intent i =new Intent(getActivity(),DetailsActivity.class);
        i.putExtra("plcae",gd.getCurrentCity()+","+gd.getCurrentState());
        i.putExtra("list",hw);
        i.putExtra("pos",pos);
        startActivity(i);

    }

    public void setupAdapterData(ArrayList<HourlyWeather> hourlyWeatherList) {

        Log.v("list",hourlyWeatherList.toString());
        hourlyWeatherAdapter = new HourlyDataAdapter(getActivity(), R.layout.image_text_layout, hourlyWeatherList);
        hourlyWeatherAdapter.setNotifyOnChange(true);
        setListAdapter(hourlyWeatherAdapter);

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
        protected void onPostExecute(ArrayList<HourlyWeather> bd)
        {
            hw=bd;
            super.onPostExecute(bd);
            setupAdapterData(bd);

        }

    }
}