package com.hongmin.lostcitiesclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class JoinActivity extends Activity {
    private EditText etUsrIdJoin, etUsrPwdJoin, etUsrPwdConfirm;
    private EditText etUsrName, etUsrEmail;
    private Button btJoin, btGoLogin;
    private ApplicationClass appclass;
    private ApplicationClass.AppState appstate;
    private String dn = "join";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        etUsrIdJoin = (EditText)findViewById(R.id.etUsrIdJoin);
        etUsrPwdJoin = (EditText)findViewById(R.id.etUsrPwdJoin);
        etUsrPwdConfirm = (EditText)findViewById(R.id.etUsrPwdConfirm);
        etUsrName = (EditText)findViewById(R.id.etUsrName);
        etUsrEmail = (EditText)findViewById(R.id.etUsrEmail);
        btJoin  = (Button)findViewById(R.id.btJoin);
        btGoLogin = (Button)findViewById(R.id.btGoLogin);

        appclass = (ApplicationClass)getApplicationContext();

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int vid = v.getId();
                if(vid == R.id.btJoin)
                    doJoin();
                else if(vid == R.id.btGoLogin)
                    finish();

                /*switch(v.getId()){
                    case R.id.btJoin:
                        doJoin();
                        break;
                    case R.id.btGoLogin:
                        finish();
                        break;
                    default:
                        break;
                }*/
            }
        };

        btJoin.setOnClickListener(listener);
        btGoLogin.setOnClickListener(listener);
    }

    private void doJoin(){
        String usrId, usrPwd, usrPwdCf,  usrName, usrEmail;
        usrId = etUsrIdJoin.getText().toString();
        usrPwd = etUsrPwdJoin.getText().toString();
        usrPwdCf = etUsrPwdConfirm.getText().toString();
        usrName = etUsrName.getText().toString();
        usrEmail = etUsrEmail.getText().toString();

         if(!usrPwd.equals(usrPwdCf))
         {
              Toast.makeText(this,"비밀번호와 비밀번호 확인이 다릅니다.",Toast.LENGTH_LONG).show();
              return;
         }

         String msg =  appstate.JOIN.val + "/"+appclass.CTS+"/" + usrId +"/"+usrPwd+"/"+usrName+"/"+usrEmail;
         appclass.sender.writeLine(msg);
         String[] msgs = appclass.receiver.getReadMsg().split("/");
         if(Integer.parseInt(msgs[appclass.STAT]) == appclass.SCS){
              Toast.makeText(this,"완료되었습니다.",Toast.LENGTH_LONG).show();
          finish();
        }
        else{
            Toast.makeText(this,"ID가 중복 됩니다. 다른 ID를 사용하여 주세요.",Toast.LENGTH_LONG).show();
        }
    }

}
