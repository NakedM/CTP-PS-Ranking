package com.project.ctp.psrankingclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

    private Button btn_moveRankingAct;
    private Button btn_moveSearchProblemAct;
    private Button btn_moveSettingAct;
    private Button btn_exit;
    private String an = "main"; //activity name 로그용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_moveRankingAct = (Button)findViewById(R.id.btn_moveRankingAct);
        btn_moveSearchProblemAct = (Button)findViewById(R.id.btn_moveSearchProblemAct);
        btn_moveSettingAct = (Button)findViewById(R.id.btn_moveSettingAct);
        btn_exit = (Button)findViewById(R.id.btn_exit);

        Intent intent_moveToSplashActivity = new Intent(this, SplashActivity.class);
        startActivity(intent_moveToSplashActivity);

        View.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent intent;
                switch(view.getId())
                {
                    case R.id.btn_moveRankingAct: //랭킹 액티비티로 이동
                        intent = new Intent(MainActivity.this, RankingActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_moveSearchProblemAct: //검색 액티비티로 이동
                        intent = new Intent(MainActivity.this, SearchProblemActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_moveSettingAct: //설정 액티비티로 이동
                        intent = new Intent(MainActivity.this, SettingActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btn_exit:
                        MainActivity.super.finish();
                        break;
                }
            }
        };



        btn_moveRankingAct.setOnClickListener(listener);
        btn_moveSearchProblemAct.setOnClickListener(listener);
        btn_moveSettingAct.setOnClickListener(listener);
        btn_exit.setOnClickListener(listener);
    }


}
