package com.project.ctp.psrankingclient;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Sangyun on 2015-06-13.
 */
public class Crawler extends Thread
{
    String url;
    String stream;

    Crawler(String url)
    {
        this.url = url;
        stream="";
    }

    public void run()
    {
        Log.i("myCRAWLER", "begin");
        try {

            URL myServer = new URL(url);
            URLConnection Connection = myServer.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(Connection.getInputStream(), "UTF-8"));
            String inputLine, ret = "";
            StringBuilder a = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                stream += inputLine;
            in.close();
        }  catch(MalformedURLException e){
            Log.i("myCRAWLER", "URL exception");
            e.printStackTrace();
        }  catch(IOException e) {
            Log.i("myCRAWLER", "IO exception");
            e.printStackTrace();
        }
        Log.i("myCRAWLER", "end");
    }

    public String getStream()
    {
        return stream;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }
}
