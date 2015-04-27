package com.project.ctp.psrankingclient;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;


public class SplashActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);


        Handler handler = new Handler();

        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                finish();
            }
        },1000); //1.5초
    }
}
