/*
File Name:    RequestParams.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

public class RequestParams implements Serializable {
    String method,baseUrl;
    HashMap<String,String> params = new HashMap<String,String>();
    public RequestParams(String method, String baseUrl) {
        super();
        this.method = method;
        this.baseUrl = baseUrl;
    }
    public void addParams(String key, String value){
        params.put(key, value);
    }
    public String getEncodedParams() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        for(String key:params.keySet()){
            String value = URLEncoder.encode(params.get(key));
            if(sb.length()>0){
                sb.append("&");
            }
            sb.append(key+"="+value);
        }
        return sb.toString();
    }
    public String getEncodedUrl() throws UnsupportedEncodingException {
        return this.baseUrl+"?"+getEncodedParams();
    }
    public HttpURLConnection setupConnection () throws IOException {
        if(method.equals("GET")) {
            URL url =new URL(getEncodedUrl());
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            return con;
        } else {
            URL url =new URL(this.baseUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(con.getOutputStream());
            writer.write(getEncodedParams());
            writer.flush();
            return con;
        }
    }
}