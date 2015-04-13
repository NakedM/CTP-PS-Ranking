package com.hongmin.lostcitiesclient;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Intern15041 on 2015-02-25.
 */
public class ClientReceiver extends Thread {
    Socket socket;
    BufferedReader in;
    //String readMsg;
    Queue<String> readMsgs;
    String dn = "receiver";

    public ClientReceiver(Socket socket) throws IOException{
        this.socket=socket;
        readMsgs = new LinkedList<String>();
        //Collections.synchronizedCollection(readMsgs);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        while(in!=null){
            readMsgs.offer(readLine());
        }
    }
    public String readLine(){
        try {
            String tmp;
            //readReady = false;
            tmp = in.readLine();
            Log.d(dn, "read " + tmp);
            return tmp;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public String getReadMsg(){
        while(true){
            if(!readMsgs.isEmpty()){
                Log.d(dn, "poll  " + readMsgs);
                return readMsgs.poll();
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void close() throws  IOException{
        in.close();
        in = null;
        Log.d("receiver","close");
    }
}
