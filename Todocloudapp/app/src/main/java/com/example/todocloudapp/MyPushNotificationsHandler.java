package com.example.todocloudapp;

import android.content.Context;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.microsoft.windowsazure.notifications.NotificationsHandler;

/**
 * Created by Mandeep on 4/25/2017.
 */

public class MyPushNotificationsHandler extends NotificationsHandler
{

    @com.google.gson.annotations.SerializedName("handle")
    private static String mHandle;

    // Returns the handle
    public static String getHandle() { return mHandle; }

    // Sets the handle
    public static final void setHandle(String handle) { mHandle = handle; }

    @Override
    public void onRegistered(Context context, String gcmRegistrationId)
    {
        super.onRegistered(context, gcmRegistrationId);

        // + Support push notifications to users...
        MobileServiceClient client = ToDoActivity.getClient();
        MobileServiceTable<Channel> registrations = client.getTable(Channel.class);

        // Create a new Registration
        Channel channel = new Channel();
        channel.setHandle(gcmRegistrationId);

        // Insert the new Registration
        registrations.insert(channel, new TableOperationCallback<Channel>() {

        public void onCompleted(Channel entity, Exception exception, ServiceFilterResponse response) {

            if (exception != null) {
                Log.e("PushHandler", exception.getMessage());
            } else {
                Log.i("PushHandler", "Registration OK");
            }
        }
    });
    }
}
