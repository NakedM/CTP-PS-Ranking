package com.project.ctp.psrankingclient;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Thread 클래스로 json 크롤링 실행
 * Created by Sangyun on 2015-06-13.
 */
public class Crawler extends Thread
{
    String url;
    String stream;

    /**
     * @param url 크롤링할 주소 변수
     */
    Crawler(String url)
    {
        this.url = url;
        stream="";
    }

    /**
     * 	스레드 실행 및 json 크롤링
     */
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

    /**
     * @return string 크롤링한 스트링 결과 리턴
     */
    public String getStream()
    {
        return stream;
    }

    /**
     * @param url URL 주소 재설정
     */
    public void setUrl(String url)
    {
        this.url = url;
    }
}
