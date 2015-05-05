package com.project.ctp.psrankingclient;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class SettingActivity extends ActionBarActivity {
    Button btn_idSet;
    EditText et_bojId;
    String bojId;
    String dn = "setting";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btn_idSet = (Button)findViewById(R.id.btn_idSet);
        et_bojId = (EditText)findViewById(R.id.et_bojId);

        readBojId();

        btn_idSet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                storeBojId();
            }
        });
    }

    void readBojId(){
        try {
            FileInputStream fis = openFileInput("userIds.txt");

            byte[] buf = new byte[fis.available()];
            fis.read(buf);
            String str = new String(buf);
            et_bojId.setText(str);
            fis.close();
        }catch (IOException e) {
            Log.e(dn,e.getMessage());
            e.getStackTrace().toString();

        }

    }

    void storeBojId(){
        bojId = et_bojId.getText().toString();
        if(bojId.isEmpty()){
            return;
        }

        try {
            FileOutputStream fos = openFileOutput("userIds.txt", Context.MODE_ENABLE_WRITE_AHEAD_LOGGING);

            byte[] strByte = bojId.getBytes();
            fos.write(strByte);
            fos.close();

        }catch (IOException e) {
            Log.e(dn,e.getMessage());
            e.getStackTrace().toString();

        }

    }




}
