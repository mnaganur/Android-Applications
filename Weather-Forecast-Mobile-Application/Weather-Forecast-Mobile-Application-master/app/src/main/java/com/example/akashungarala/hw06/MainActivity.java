/*
File Name:    MainActivity.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DBHelper database;
    CitiesAdapter citiesAdapter;
    ListView lv;
    RelativeLayout rl;
    TextView noCityDisplay;
    ArrayList<SavedCityState> savedCityStateList;
    GlobalData gb;
    DatabaseDataManager ddm;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        //Action Bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        noCityDisplay = (TextView)findViewById(R.id.noCityDisplay);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sun_cloud);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Homework 5");

        gb=(GlobalData)getApplication();
        ddm = new DatabaseDataManager(this);
        gb.setDdm(ddm);


        lv = (ListView) findViewById(R.id.listView);
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           int index, long arg3) {
                // TODO Auto-generated method stub

                SavedCityState scs=(SavedCityState)lv.getItemAtPosition(index);

                database.deleteFromTableBasedOnType("city", scs.getCity(), "cities");
                savedCityStateList = database.getAllSavedCityState();
                for(Integer i=0;i<savedCityStateList.size();i++){
                    RequestParams rp1 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/hourly/q/" +savedCityStateList.get(i).getState()+ "/" + savedCityStateList.get(i).getCity()+".xml");
                    Bundle input = new Bundle();
                    input.putSerializable("rps",rp1);
                    input.putInt("i", i);
                    new GetDataWithParams().execute(input);
                }
                setupAdapterData(savedCityStateList);

                if(savedCityStateList.size()==0)
                {
                    noCityDisplay.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), CityDataActivity.class);
                SavedCityState scs=(SavedCityState)lv.getItemAtPosition(position);
                String m=scs.getCity().replace(' ','_');
                i.putExtra("city",m);
                i.putExtra("state",scs.getState());
                startActivity(i);

            }
        });

        rl = (RelativeLayout)findViewById(R.id.relativeLayout);
        database = new DBHelper(this);




        Integer numberOfRowsForCitiesTable = database.numberOfRowsForCitiesTable();
        if(numberOfRowsForCitiesTable>0){
            noCityDisplay.setVisibility(View.INVISIBLE);
            savedCityStateList = database.getAllSavedCityState();
            for(Integer i=0;i<savedCityStateList.size();i++)
            {
                String city=savedCityStateList.get(i).getCity().trim();
                String city2 = city.replace(' ','_');

                Log.d("city", city2);
                RequestParams rp1 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/hourly/q/" +savedCityStateList.get(i).getState()+ "/" + city2+".xml");
                //  RequestParams rp1 = new RequestParams("GET", "http://api.wunderground.com/api/2ccf82ffe190f1fd/hourly/q/" +savedCityStateList.get(i).getState()+ "/" + savedCityStateList.get(i).getCity()+".xml");
                Bundle input = new Bundle();
                input.putSerializable("rps",rp1);
                input.putInt("i", i);
                new GetDataWithParams().execute(input);
            }

        }else{
            noCityDisplay.setVisibility(View.VISIBLE);
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.add_city) {
            Intent it=new Intent(this,AddCityActivity.class);
            startActivity(it);
            finish();
            return true;
        }else if(id == R.id.view_notes){
            Intent it=new Intent(this,NotesActivity.class);
            startActivity(it);
        }
        else{
            database.deleteAllRecords("cities");
            savedCityStateList = database.getAllSavedCityState();
            noCityDisplay.setVisibility(View.VISIBLE);
            setupAdapterData(savedCityStateList);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setupAdapterData(ArrayList<SavedCityState> savedCityStateList) {


        citiesAdapter = new CitiesAdapter(this,R.layout.two_textview_layout, savedCityStateList);
        citiesAdapter.setNotifyOnChange(true);
        lv.setAdapter(citiesAdapter);
    }

    protected void onDestroy() {
        database.close();
        super.onDestroy();
    }

    class GetDataWithParams extends AsyncTask<Bundle,Integer,Bundle> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bundle doInBackground(Bundle... params) {
            RequestParams rps=(RequestParams)params[0].getSerializable("rps");
            try {
                HttpURLConnection con = rps.setupConnection();
                con.setRequestMethod("GET");
                InputStream in = con.getInputStream();
                Bundle bd = new Bundle();
                bd.putSerializable("hourlyWeatherList", InputStreamConverterUtil.FullParser.getCurrentTemp(in));
                bd.putInt("i", params[0].getInt("i"));
                return bd;

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
        protected void onPostExecute(Bundle bd) {
            super.onPostExecute(bd);
            ArrayList<HourlyWeather>list = (ArrayList<HourlyWeather>)bd.getSerializable("hourlyWeatherList");
            if(list.size()==0){}else {
                HourlyWeather result = (HourlyWeather) list.get(0);
                String currentTemp = result.getTemperature();


                savedCityStateList.get(bd.getInt("i")).setTemp(currentTemp);
                setupAdapterData(savedCityStateList);
            }




        }

    }


}