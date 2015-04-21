package com.project.ctp.psrankingclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view)
    {
        switch(view.getId())
        {
            case R.id.btn_moveToRankingActivity: //랭킹 액티비티로 이동
                Intent intent_moveToRanking = new Intent(this, RankingActivity.class);
                startActivity(intent_moveToRanking);
                break;
            case R.id.btn_moveToSearchProblemActivity: //검색 액티비티로 이동
                Intent intent_moveToSearch = new Intent(this, SearchProblemActivity.class);
                startActivity(intent_moveToSearch);
                break;
            case R.id.btn_moveToSettingActivity: //설정 액티비티로 이동
                Intent intent_moveToSetting = new Intent(this, SettingActivity.class);
                startActivity(intent_moveToSetting);
                break;
            case R.id.btn_exit:
                super.finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
