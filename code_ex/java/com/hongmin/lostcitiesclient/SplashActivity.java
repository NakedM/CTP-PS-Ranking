package com.hongmin.lostcitiesclient;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

//시작시 splash 화면을 보여주는 activity입니다.
public class SplashActivity extends Activity {
    String dn = "main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable(){
            @Override
            public void run() {
                finish();
            }
        },1500); //1.5초
    }
}
