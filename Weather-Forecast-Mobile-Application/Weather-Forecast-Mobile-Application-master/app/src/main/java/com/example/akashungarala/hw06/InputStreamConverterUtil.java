/*
File Name:    InputStreamConverterUtil.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class InputStreamConverterUtil {

    static public class JsonParser {
        static public ArrayList<LocationValidationResult> parseObject(InputStream in) throws IOException, JSONException {
            ArrayList<LocationValidationResult> resultList = new ArrayList<LocationValidationResult>();
            String s = new InputStreamToString().convert(in);
            Log.v("result string", s);
            JSONObject root = new JSONObject(s);
            LocationValidationResult result = new LocationValidationResult();
            result.setLikelihood(root.getString("likelihood"));
            result.setNormalizedLocation(root.getString("normalizedLocation"));
            resultList.add(result);
            return resultList;
        }
        }
    static public class FullParser extends DefaultHandler {
        static public ArrayList<HourlyWeather> getCurrentTemp (InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            HourlyWeather hourlyWeather = null;
            ArrayList<HourlyWeather> objectsList=new ArrayList<HourlyWeather>();
            int event = parser.getEventType();
            String tag = "starting";
            Integer tempCounter=0;
            Boolean forecastTag=false;
            Boolean tempTag=false;
            while(event != XmlPullParser.END_DOCUMENT&&tempCounter<1){
                switch(event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("forecast")){
                            hourlyWeather = new HourlyWeather();
                            //hourlyWeather.setId(parser.getAttributeValue(null, "id"));
                            forecastTag=true;
                        }else if(parser.getName().equals("temp")&&forecastTag){
                            tempTag = true;
                        }else if(forecastTag&&tempTag&&parser.getName().equals("english")){
                            hourlyWeather.setTemperature((parser.nextText().trim()));
                            Log.v("setTemperature!!!", hourlyWeather.getTemperature());
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("forecast")){
                            forecastTag=false;
                        }else if(forecastTag&&parser.getName().equals("temp")){
                        tempTag=false;
                    } else if(forecastTag&&!tempTag&&parser.getName().equals("english")){
                            Log.v("getTemperature", hourlyWeather.getTemperature().toString());
                            Log.v("tempCounter", tempCounter.toString());
                            Log.v("forecastT",forecastTag.toString());
                            Log.v("tempT",tempTag.toString());
                            tempCounter++;

                            objectsList.add(hourlyWeather);

                            hourlyWeather = null;
                }

                        break;
                    default:
                        break;
                }
                event = parser.next();
            }


            return objectsList;
        }

        static public ArrayList<HourlyWeather> getAllHourlyData (InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
           // HourlyWeather hourlyWeather = null;
            ArrayList<HourlyWeather> weatherList=new ArrayList<HourlyWeather>();
            HourlyWeather weather = null;
            ArrayList<HourlyWeather> objectsList = new ArrayList<HourlyWeather>();
            //int event = parser.getEventType();
            Integer tempCounter = 0;
            Boolean forecastTag = false;
            Boolean tempTag = false;
            Boolean fcttimeTag = false;
            int event=parser.getEventType();
            int flag = 0,flag2=0;

            while (event != XmlPullParser.END_DOCUMENT) {
                switch (event) {

                    case XmlPullParser.START_TAG:
                        if (parser.getName().equals("forecast")) {

                            weather = new HourlyWeather();
                            flag = 0;
                        } else if (parser.getName().equals("hour")) {
                            int hr = Integer.parseInt(parser.nextText().trim());
                            String time;
                            {
                                if (hr > 12) {
                                    hr = hr % 12;
                                    time = "pm";
                                } else {
                                    time = "am";
                                }
                            }
                            weather.setTime(hr + ":00" + time);

                        } else if (parser.getName().equals("english")) {
                            flag++;
                            if (flag == 1) {
                                weather.setTemperature(parser.nextText());

                            } else if (flag == 2) {
                                weather.setDewpoint(parser.nextText());
                            } else if (flag == 3) {
                                weather.setWindSpeed(parser.nextText());
                            } else if (flag == 6) {
                                weather.setFeelsLike(parser.nextText());
                            }


                        } else if (parser.getName().equals("condition")) {
                            weather.setClouds(parser.nextText().trim());

                        } else if (parser.getName().equals("icon_url")) {
                            weather.setIconUrl(parser.nextText().trim());

                        } else if (parser.getName().equals("wx")) {
                            weather.setClimateType(parser.nextText().trim());

                        } else if (parser.getName().equals("humidity")) {
                            weather.setHumidity(parser.nextText().trim());

                        } else if (parser.getName().equals("mslp")) {
                            flag2 = 1;
                        } else if (parser.getName().equals("metric") && flag2 == 1) {
                            weather.setPressure(parser.nextText().trim());
                            flag2 = 0;
                        } else if (parser.getName().equals("wdir")) {
                            event = parser.nextTag();
                            if (event == XmlPullParser.START_TAG) {
                                weather.setWindDirection(parser.nextText().trim());
                            }
                            event = parser.nextTag();
                            if (event == XmlPullParser.START_TAG) {
                                weather.setWindDegree(parser.nextText().trim());
                            }

                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("forecast")) {
                            weatherList.add(weather);
                        }

                        break;
                }

                event = parser.next();
            }


            int min=0;//Integer.parseInt(objectsList.get(0).getTemperature());
            int max=min;
            for(HourlyWeather w:objectsList)
            {
                if(min > Integer.parseInt(w.getTemperature()))
                {
                    Log.d("minmax1",min+"  "+Integer.parseInt(w.getTemperature()));
                    min = Integer.parseInt(w.getTemperature());
                }

                if(max < Integer.parseInt(w.getTemperature()))
                {
                    max = Integer.parseInt(w.getTemperature());
                }

            }
            Log.d("minmax",min+"     "+max);
            for(HourlyWeather w:objectsList)
            {
                w.setMaximumTemp(max+"");
                w.setMinimumTemp(min+"");
            }


            return weatherList;

        }


        static public ArrayList<HourlyWeather> getForecastData (InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in, "UTF-8");
            HourlyWeather hourlyWeather = null;
            ArrayList<HourlyWeather> objectsList=new ArrayList<HourlyWeather>();
            int event = parser.getEventType();
            Integer tempCounter=0;
            Boolean forecastTag=false;
            Boolean tempTag=false;
            Boolean fcttimeTag=false;
            Boolean dateTag=false;
            Boolean simpleforecastTag=false,highTag=false,lowTag=false;
            while(event != XmlPullParser.END_DOCUMENT){
                switch(event){
                    case XmlPullParser.START_TAG:
                        if(parser.getName().equals("simpleforecast")){
                            simpleforecastTag=true;
                        }
                        else if(simpleforecastTag&&parser.getName().equals("forecastday")){
                            hourlyWeather = new HourlyWeather();
                            //hourlyWeather.setId(parser.getAttributeValue(null, "id"));
                            forecastTag=true;
                        }else if(simpleforecastTag&&parser.getName().equals("temp")&&forecastTag){
                            tempTag = true;
                        }else if(simpleforecastTag&&forecastTag&&parser.getName().equals("date")){
                            dateTag=true;
                        }
                        else if(simpleforecastTag&&forecastTag&&dateTag&&parser.getName().equals("day")){
                            hourlyWeather.setDay((parser.nextText().trim()));
                        }
                        else if(simpleforecastTag&&forecastTag&&dateTag&&parser.getName().equals("monthname")){
                            hourlyWeather.setMonth((parser.nextText().trim()));

                        }
                        else if(simpleforecastTag&&forecastTag&&dateTag&&parser.getName().equals("year")){
                            hourlyWeather.setYear((parser.nextText().trim()));
                        }
                        else if(simpleforecastTag&&forecastTag&&parser.getName().equals("high")){
                            highTag=true;
                        }
                        else if(simpleforecastTag&&forecastTag&&highTag&&parser.getName().equals("fahrenheit")){
                            hourlyWeather.setMaximumTemp((parser.nextText().trim()));
                        }
                        else if(simpleforecastTag&&forecastTag&&parser.getName().equals("low")){
                            lowTag=true;
                        }
                        else if(simpleforecastTag&&forecastTag&&lowTag&&parser.getName().equals("fahrenheit")){
                            hourlyWeather.setMinimumTemp((parser.nextText().trim()));
                        }
                        else if (simpleforecastTag&&forecastTag && tempTag &&parser.getName().equals("english")){
                            hourlyWeather.setTemperature((parser.nextText().trim()));

                        }else if(simpleforecastTag&&forecastTag&&!tempTag&&parser.getName().equals("icon_url")) {
                            hourlyWeather.setIconUrl((parser.nextText().trim()));

                        }else if(simpleforecastTag&&forecastTag&&parser.getName().equals("FCTTIME")){
                            fcttimeTag=true;
                        }else if(simpleforecastTag&&forecastTag&&fcttimeTag&&parser.getName().equals("civil")){
                            hourlyWeather.setTime((parser.nextText().trim()));
                        }else if(simpleforecastTag&&forecastTag&&parser.getName().equals("conditions")){
                            hourlyWeather.setClimateType((parser.nextText().trim()));
                        }

                        break;
                    case XmlPullParser.END_TAG:
                        if(simpleforecastTag&&parser.getName().equals("forecastday")){
                            forecastTag=false;
                            objectsList.add(hourlyWeather);
                            hourlyWeather = null;
                        }else if(simpleforecastTag&&forecastTag&&parser.getName().equals("temp")){
                            tempTag=false;
                        }
                        else if(simpleforecastTag&&forecastTag&&parser.getName().equals("date")){
                            dateTag=false;
                        }else if(simpleforecastTag&&forecastTag&&parser.getName().equals("high")){
                            highTag=false;
                        }
                        else if(simpleforecastTag&&forecastTag&&parser.getName().equals("low")){
                            lowTag=false;
                        }
                        else if(simpleforecastTag&&forecastTag&&!tempTag&&parser.getName().equals("english")){
                            Log.v("getTemperature", hourlyWeather.getTemperature().toString());
                            Log.v("tempCounter", tempCounter.toString());
                            Log.v("forecastT",forecastTag.toString());
                            Log.v("tempT",tempTag.toString());
                            //tempCounter++;
                        }else if(simpleforecastTag&&forecastTag&&parser.getName().equals("FCTTIME")){
                            fcttimeTag=false;
                        }
                        else if(!forecastTag&&parser.getName().equals("simpleforecast")){
                            simpleforecastTag=false;
                        }

                        break;
                    default:
                        break;
                }
                event = parser.next();
            }
            Log.v("forecast list length", String.valueOf(objectsList.size()));
            return objectsList;
        }

    }

}






