package com.hongmin.lostcitiesclient;

import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * non block I/0을 실험하기 위한 클래스
 * 현재 제출을 위해 이 클래스는 제출 되지 않음.
 */
public class ClientChannel extends Thread{
    private SocketChannel client;
    private int port = 7779;
    Selector selector = null;
    Charset cs = Charset.forName("US-ASCII");
    private String ip = "119.206.203.133";
    Queue<String> readMsgs;

    public ClientChannel(){
        readMsgs = new LinkedList<String>();
    }

    @Override
    public void run() {
        super.run();
        try {
            client = SocketChannel.open(new InetSocketAddress(ip, port));
            client.configureBlocking(false);
            //client.connect(new InetSocketAddress(ip, port));
            //client.finishConnect();
            //connect로 해서 연결할경우 finishconnect를 꼭 해줘야됨.

            selector = Selector.open();
            client.register(selector, SelectionKey.OP_READ);
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            Thread.sleep(1000);
            write("hello");
            while(true) {
                selector.select();
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while (!iter.hasNext()) {
                    SelectionKey key = iter.next();
                    Log.d("key",key.toString());
                    if (key.isReadable()) {
                        int len = client.read(buffer);
                        if (len < 0) {
                            System.out.println("Client :: server closed");
                            break;
                        } else if (len == 0) {
                            continue;
                        }
                        buffer.flip();

                        CharBuffer cb = cs.decode(buffer);
                        Log.d("read", cb.toString());
                        //System.out.printf("From Server : ");
                        while (cb.hasRemaining()) {
                            //System.out.printf("%c", cb.get());
                        }
                        // System.out.println();
                        clearBuffer(buffer);
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void write(String str){
        try {
            ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
            clearBuffer(buffer);
            buffer.put(str.getBytes());
            buffer.flip();
            if(client != null && client.isConnected()) {
                while (buffer.hasRemaining()) {
                    Log.d("write", buffer.toString());
                    client.write(buffer);
                }
            }

            buffer.rewind();
            //ystem.out.printf("[write :: text : %s / len : %d]\n", str, len);
        }catch(IOException e){
            e.getStackTrace();
        }
    }

    private void clearBuffer(ByteBuffer buffer) {
        // TODO Auto-generated method stub
        if(buffer != null){
            buffer.clear();
            buffer = null;
        }
    }
}
