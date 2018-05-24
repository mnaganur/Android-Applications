/*
File Name:    ForecastDataFragment.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class ForecastDataFragment extends ListFragment implements AdapterView.OnItemLongClickListener
{

    GlobalData gd;
    ForecastDataAdapter forecastWeatherAdapter;
    DatabaseDataManager ddm;
    ProgressDialog pd;
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.d("mark", "onCreate()--------->news Fragment");


        pd = new ProgressDialog(getActivity());
        pd.setMessage("Loading Forecast Data");
        pd.setCancelable(false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.hourly_data_fragment, container, false);


        Log.d("mark", "onCreateView()--------->hourly Fragment");
        gd = (GlobalData) getActivity().getApplication();

        RequestParams rp2 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/forecast10day/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
        new GetForecastDataWithParams().execute(rp2);

        return view;
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        getListView().setOnItemLongClickListener(this);
    }

    @Override
    public void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.d("mark", "onPause()--------->news Fragment");
    }

    public void onListItemClick(ListView l, View v, int pos, long id) {
        super.onListItemClick(l, v, pos, id);
        Intent i = new Intent(this.getActivity(), AddNotesActivity.class);
        GlobalData gd = (GlobalData) this.getActivity().getApplication();
        HourlyWeather hw = (HourlyWeather)l.getItemAtPosition(pos);
        gd.setToSaveDate(hw.getDay() + hw.getMonth() + hw.getYear() + gd.getCurrentState() + gd.getCurrentCity());
        Log.v("gd!!!", gd.getToSaveDate());
        //Toast.makeText(getActivity(), "Item " + pos + " was Short clicked", Toast.LENGTH_SHORT).show();
        startActivity(i);
        this.getActivity().finish();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> l, View view, int position, long id) {
        HourlyWeather hw = (HourlyWeather) l.getItemAtPosition(position);
        ddm = gd.getDdm();
        ddm.getNoteTableDAO().deleteNotesByType(NoteTable.COLUMN_DATE, hw.getDay()+hw.getMonth()+"2016"+gd.getCurrentState()+gd.getCurrentCity());
        RequestParams rp2 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/forecast10day/q/" + gd.getCurrentState() + "/" + gd.getCurrentCity() + ".xml");
        new GetForecastDataWithParams().execute(rp2);
       Toast.makeText(getActivity(), "Note has been Removed", Toast.LENGTH_SHORT).show();
        return true;
    }

    public void setupAdapterData(ArrayList<HourlyWeather> hourlyWeatherList) {

        forecastWeatherAdapter = new ForecastDataAdapter(getActivity(), R.layout.image_text_layout, hourlyWeatherList);
        forecastWeatherAdapter.setNotifyOnChange(true);
        setListAdapter(forecastWeatherAdapter);

    }



    class GetForecastDataWithParams extends AsyncTask<RequestParams,Integer,ArrayList<HourlyWeather>> {
        @Override
        protected void onPreExecute() {

            pd.show();
            super.onPreExecute();
        }

        @Override
        protected ArrayList<HourlyWeather> doInBackground(RequestParams... params) {
            Log.v("doing forecast", "@");
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
        protected void onPostExecute(ArrayList<HourlyWeather> result)
        {

            super.onPostExecute(result);
            setupAdapterData(result);
            pd.dismiss();

        }

    }



}