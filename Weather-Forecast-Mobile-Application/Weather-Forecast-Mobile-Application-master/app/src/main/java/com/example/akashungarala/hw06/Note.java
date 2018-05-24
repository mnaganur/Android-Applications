/*
File Name:    Note.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;


public class Note {
    private long _id;
    String savedDate;
    String savedContent;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getSavedDate() {
        return savedDate;
    }

    public void setSavedDate(String savedDate) {
        this.savedDate = savedDate;
    }

    public String getSavedContent() {
        return savedContent;
    }

    public void setSavedContent(String savedContent) {
        this.savedContent = savedContent;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("id:"+_id);
        sb.append("date:"+savedDate);
        sb.append("note:"+savedContent);
        return sb.toString();
    }
}
