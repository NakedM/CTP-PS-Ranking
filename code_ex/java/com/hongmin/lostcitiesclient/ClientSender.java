package com.hongmin.lostcitiesclient;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by Intern15041 on 2015-02-25.
 */
public class ClientSender{
    Socket socket;
    PrintWriter out;

    ClientSender(Socket socket) throws  IOException{
        this.socket = socket;
        out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
    }

    public void writeLine(String msg){
            out.println(msg);
            out.flush();
    }

    public void close(){
        out.close();
        out = null;
    }
}
