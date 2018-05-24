package com.example.jason.serverandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private static final int portnumber = 8080;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ServerSocket serverSocket = null;
        try
        {

            System.out.println("Server starting at portnumber: " +portnumber);
            serverSocket = new ServerSocket(portnumber);

            //Client Connecting
            System.out.println("Waiting for client to connect...");
            Socket socket = serverSocket.accept();
            System.out.println("Client has connected");

            //Send message to client
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bw.write("Hi. This is the message from Server.");
            bw.newLine();
            bw.flush();

            //Receive message from client
            BufferedReader br = new BufferedReader (new InputStreamReader(socket.getInputStream()));

            String data;
            while ((data = br.readLine()) != null)
                System.out.println("Message from client: " + data);

            System.out.println("Server has ended...");

        }
        catch (IOException e)
        {

            e.printStackTrace();

        }

    }
}


