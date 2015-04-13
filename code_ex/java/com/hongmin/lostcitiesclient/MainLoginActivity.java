package com.hongmin.lostcitiesclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 이 프로젝트 파일 안에 UnityGame과 app모듈이 있는데
 * app 모듈이 라이브러리이며 UnityGame이 메인 모듈임
 * build.gradle에서 확인 가능
 *
 * 로그인을 담당하는 Activity
 */

public class MainLoginActivity extends Activity {
    private Button btLogin, btGoJoin;
    private EditText etUsrId, etUsrPwd;
    private String dn = "login";
    private Boolean isLogin = false;                //로그인 성공상태를 나타내는 변수
    private ApplicationClass appclass;
    private ApplicationClass.AppState appstate;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        btLogin = (Button)findViewById(R.id.btLogin);
        btGoJoin = (Button)findViewById(R.id.btGoJoin);
        etUsrId = (EditText)findViewById(R.id.etUsrId);
        etUsrPwd = (EditText)findViewById(R.id.etUsrPwd);
        appclass = (ApplicationClass)getApplicationContext();

        //splash 화면을 띄음
        Intent intent = new Intent(this, SplashActivity.class);
        startActivity(intent);

         View.OnClickListener listener = new View.OnClickListener(){
            public void onClick(View v){
                int vid = v.getId();
                if(vid == R.id.btLogin)         //Login 클릭
                    doLogin();
                else if(vid == R.id.btGoJoin){  //Join 클릭
                    etUsrId.setText("");
                    etUsrPwd.setText("");
                    Intent intent = new Intent(MainLoginActivity.this,JoinActivity.class);
                    startActivity(intent);
                }
                //현재 안드로이드 프로젝트는 라이브러리로 되어있음.
                //라이브러리 프로젝트는 R.java때문에 switch case문을 사용할 수가 없음.
                /*switch(v.getId()){
                    case R.id.btLogin:
                        doLogin();
                        break;
                    case R.id.btGoJoin:
                        Intent intent = new Intent(MainLoginActivity.this,JoinActivity.class);
                        startActivity(intent);
                        break;
                    default :
                        break;
                }*/
            }
        };
        btLogin.setOnClickListener(listener);
        btGoJoin.setOnClickListener(listener);
    }

    public void doLogin(){
        String usrid, usrpwd;
        usrid = etUsrId.getText().toString();
        usrpwd = etUsrPwd.getText().toString();
        if(usrid.length() < 1 || usrpwd.length() < 1 || usrid.length() > 19 || usrpwd.length() > 19) {
            Toast.makeText(this,"비정상적인 입력입니다.",Toast.LENGTH_LONG).show();
            return;
    }

    String msg =  appstate.LOGIN.val + "/"+appclass.CTS+"/" + usrid +"/"+usrpwd;
    appclass.sender.writeLine(msg);
        String[] msgs = appclass.receiver.getReadMsg().split("/");
        if(Integer.parseInt(msgs[appclass.STAT]) == appclass.SCS){
            appclass.setUsrId(msgs[2]);
            appclass.setUsrNum(Integer.parseInt(msgs[3]));
            Log.d(dn,msgs[2]);
            Log.d(dn,msgs[3]);
            Intent intent = new Intent(this,CenterActivity.class);
            startActivity(intent);
            isLogin = true;
            finish();
        }
        else{
            Toast.makeText(this,"Id 나 Password가 틀립니다.",Toast.LENGTH_LONG).show();
            etUsrId.setText("");
            etUsrPwd.setText("");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //로그인에 성공을 못하고 앱을 종료할 경우
        if(!isLogin)
            appclass.onDestroyApp();
    }
}

