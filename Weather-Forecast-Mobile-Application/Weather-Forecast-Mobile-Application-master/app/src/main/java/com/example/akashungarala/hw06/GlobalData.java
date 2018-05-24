/*
File Name:    GlobalData.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import android.app.Application;

import java.util.ArrayList;

public class GlobalData extends Application {
    ArrayList<HourlyWeather> allHourslyWeather;
    String toSaveDate;
    String toSaveNote;
    DatabaseDataManager ddm;

    public DatabaseDataManager getDdm() {
        return ddm;
    }

    public void setDdm(DatabaseDataManager ddm) {
        this.ddm = ddm;
    }

    public String getToSaveNote() {
        return toSaveNote;
    }

    public void setToSaveNote(String toSaveNote) {
        this.toSaveNote = toSaveNote;
    }

    public String getToSaveDate() {
        return toSaveDate;
    }

    public void setToSaveDate(String toSaveDate) {
        this.toSaveDate = toSaveDate;
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }

    public String getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(String currentCity) {
        this.currentCity = currentCity;
    }

    String currentState;
    String currentCity;

    public ArrayList<HourlyWeather> getAllHourslyWeather() {
        return allHourslyWeather;
    }

    public void setAllHourslyWeather(ArrayList<HourlyWeather> allHourslyWeather) {
        this.allHourslyWeather = allHourslyWeather;
    }



}
