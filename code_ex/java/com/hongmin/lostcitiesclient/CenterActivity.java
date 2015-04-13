package com.hongmin.lostcitiesclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CenterActivity extends Activity {
    private Button btCloseCenter, btGameStart, btShowResults;
    private ApplicationClass appclass;
    private ApplicationClass.AppState appstate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);

        btCloseCenter  = (Button)findViewById(R.id.btCloseCenter);
        btGameStart = (Button)findViewById(R.id.btGameStart);
        btShowResults = (Button)findViewById(R.id.btShowResults);

        appclass = (ApplicationClass)getApplicationContext();

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int vid = v.getId();
                if(vid == R.id.btCloseCenter)       //Close 클릭
                    finish();
                else if(vid== R.id.btGameStart)     //Game start 클릭
                    doGameStart();
                else if(vid== R.id.btShowResults){  //Show Results 클릭
                    Intent intent = new Intent(CenterActivity.this, ResultsActivity.class); //ResultsActivity로 화면을 넘김
                    startActivity(intent);
                }
                 /*
                switch(v.getId()){
                    case R.id.btCloseCenter:
                        finish();
                        break;
                    case R.id.btGameStart:
                        doGameStart();
                        break;
                    case R.id.btShowResults:
                        Intent intent = new Intent(CenterActivity.this, ResultsActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
                */
            }
        };

        btCloseCenter.setOnClickListener(listener);
        btGameStart.setOnClickListener(listener);
        btShowResults.setOnClickListener(listener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appclass.onDestroyApp();
    }

    public void doGameStart(){
        //String strmsg = appstate.GOGMAE.val+"/"+appclass.CTS+"/";
        //appclass.sender.writeLine(strmsg);
        //유저 아이디 대신 유저 넘버를 넘김(유저 넘버가 pk이기 때문) -> 게임 내에서 넘김
        //appclass.sender.writeLine(String.valueOf(appclass.getUsrNum()));

        Intent intent = new Intent("com.hongmin.unitygame"); //intent filter를 이용하여 라이브러리에서 메인 프로젝트로 화면을 넘김
        startActivity(intent);                              //UnityGame의 manifest 확인하면 intent filter있음.
    }
}
