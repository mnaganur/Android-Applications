/*
File Name:    AddCityActivity.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class AddCityActivity extends AppCompatActivity {
    DBHelper database;
    EditText cityName;
    EditText stateName;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.sun_cloud);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setTitle("Add City Activity");
        pd = new ProgressDialog(this);
        pd.setMessage("Validating Location ...");
        pd.setCancelable(false);
        cityName = (EditText) findViewById(R.id.cityName);
        stateName = (EditText) findViewById(R.id.stateName);
        Button bt = (Button) findViewById(R.id.addCity);
        bt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String a = cityName.getText().toString();
                String b = stateName.getText().toString();
                if (a.length() == 0 || b.length() == 0) {
                    Toast.makeText(getBaseContext(), "Please enter data in both fields", Toast.LENGTH_SHORT).show();
                } else if (b.length() != 2) {
                    Toast.makeText(getBaseContext(), "Please enter only 2 letter intial of state", Toast.LENGTH_SHORT).show();
                } else {
                    RequestParams rp1 = new RequestParams("GET", "https://api.fullcontact.com/v2/address/locationNormalizer.json");
                    rp1.addParams("place", a + "," + b + "," + "USA");
                    rp1.addParams("apiKey", "b8e9707d9f5bd45d");
                    new GetDataWithParams().execute(rp1);
                }
            }
        });
    }
    protected void onDestroy() {
        super.onDestroy();
    }
    class GetDataWithParams extends AsyncTask<RequestParams,Integer,ArrayList<LocationValidationResult>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.show();
        }
        @Override
        protected ArrayList<LocationValidationResult> doInBackground(RequestParams... params) {
            try {
                HttpURLConnection con = params[0].setupConnection();
                con.setRequestMethod("GET");
                InputStream in = con.getInputStream();
                return InputStreamConverterUtil.JsonParser.parseObject(in);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }
        @Override
        protected void onPostExecute(final ArrayList<LocationValidationResult> resultList) {
            super.onPostExecute(resultList);
            LocationValidationResult result = (LocationValidationResult)resultList.get(0);
            String normalizedLocation = result.getNormalizedLocation().toString();
            String likelihood = result.getLikelihood().toString();
            String [] resultArray = normalizedLocation.split(",");
            Integer lengthOfNormalizedLocation = resultArray.length;
            pd.dismiss();
            if(likelihood.equals("1.0")&&lengthOfNormalizedLocation==3&&resultArray[2].equals(" United States")){
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                database = new DBHelper(getBaseContext());
                database.insertToCitiesTable(cityName.getText().toString(), stateName.getText().toString());
                startActivity(intent);
                finish();
            }else{
                Toast.makeText(getBaseContext(), "The location can not be verified!", Toast.LENGTH_SHORT).show();
                cityName.setText("");
                stateName.setText("");
            }
        }
    }
}