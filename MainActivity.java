package com.example.kmana.unittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;


public class MainActivity extends AppCompatActivity {

    @Override



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    String udpOutputData;
    boolean StatusSendUdp;
    //-----UDP send thread
    Thread udpSendThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(100);
                }

                catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                if ( StatusSendUdp == true) {

                    try{

                        // get server name
                        InetAddress serverAddr = InetAddress.getByName("192.168.0.104");
                        Log.d("UDP", "C: Connecting...");

                        // create new UDP socket
                        DatagramSocket socket = new DatagramSocket();

                        // prepare data to be sent
                        byte[] buf = udpOutputData.getBytes();

                        // create a UDP packet with data and its destination ip & port
                        DatagramPacket packet = new DatagramPacket(buf, buf.length, serverAddr, 9876);
                        Log.d("UDP", "C: Sending: '" + new String(buf) + "'");

                        // send the UDP packet
                        socket.send(packet);
                        socket.close();

                        Log.d("UDP", "C: Sent.");
                        Log.d("UDP", "C: Done.");
                        Log.d("UDP", "C: Done.");


                    }
                    catch (Exception e)
                    {
                        Log.e("UDP", "C: Error", e);
                    }

                    try {
                        Thread.sleep(100);
                    }

                    catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    StatusSendUdp = false;
                }


            }

        }
    });

    public void sendUdp(String udpMsg) {

        udpOutputData = udpMsg;
        StatusSendUdp = true;
    }

    public void Start_OnClick(View view) {

       // String s = "hello";
        sendUdp("Hello");
        Toast.makeText(getApplicationContext(),"Hello", Toast.LENGTH_SHORT).show();



    }
}

