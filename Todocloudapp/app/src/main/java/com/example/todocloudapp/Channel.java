package com.example.todocloudapp;

/**
 * Created by Mandeep on 4/25/2017.
 */

public class Channel {
    // Push Notifications - creates handle column in db table (dynamic schema)
    @com.google.gson.annotations.SerializedName("handle")
    private String mHandle;

    // Returns the handle
    public String getHandle() { return mHandle; }

    // Sets the handle
    public final void setHandle(String handle) { mHandle = handle; }

    // Item Id
    @com.google.gson.annotations.SerializedName("id")
    private String mId;

    //Returns the item id
    public String getId() { return mId; }

    //Sets the item id - @param id : id to set
    public final void setId(String id) { mId = id; }
}
