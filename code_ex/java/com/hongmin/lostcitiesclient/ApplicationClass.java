package com.hongmin.lostcitiesclient;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * Application을 상속받음
 * 앱이 켜질 때 onCreate 실행되고
 * 앱이 꺼질 때 destroy이가 실행됨
 */
public class ApplicationClass  extends Application {
    public final int CNOT = -1;
    public final int CTS = 0;
    public final int SCS = 1;
    public final int FAIL = 2;

    public final int FLAG = 0;
    public final int STAT = 1;

    public static enum AppState{ LOGIN("LOGIN"), JOIN("JOIN"), SHOWTOT("SHOWTOT"), GOGMAE("GOGMAE"), CLOSE("CLOSE");
        public String val;
        private AppState(String val) {
            this.val = val;
        }
    }

    private String dn = "app class";
    private String ip = "119.206.203.133";
    private int port = 7779;
    private Socket socket;
    private int usrNum;
    private String usrId;
    public  ClientReceiver receiver;
    public ClientSender sender;
    //public ClientChannel channel;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(dn, "app on");

        usrNum = -1;
        new SetNetwork().execute();
    }

    public String getUsrId() { return usrId; }

    public void setUsrId(String usrId) { this.usrId = usrId; }

    public int getUsrNum() {
        return usrNum;
    }

    public void setUsrNum(int usrNum) {
        this.usrNum = usrNum;
    }

    /**
     * 소켓을 접속 시키기 위해 AsyncTask를 사용
     * 아래의 코드는 메인 쓰레드에 넣을 수가 없음. 안드로이드가 자체적으로 막고있음.
     * 인터넷을 쓰기위해 퍼미션도 필요
     */
    public class SetNetwork extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... params) {
            //channel = new ClientChannel();
            //channel.start();

            try {
                socket = new Socket();
                Log.d(dn, "create socket");
                SocketAddress sock_addr = new InetSocketAddress(ip, port);
                socket.connect(sock_addr);
                sender = new ClientSender(socket);
                Log.d(dn, "set sender");
                receiver = new ClientReceiver(socket);
                receiver.start();
                Log.d(dn, "set recevier");
            }catch (IOException e){
                Log.d(dn,e.getMessage());
            }
            return null;
        }
    }

    public void onDestroyApp(){
        try {
            sender.close();
            receiver.close();
            socket.close();
        }catch(IOException e){}
    }
}