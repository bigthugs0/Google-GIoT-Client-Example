package com.example.osama.giotclient;

/**
 * Created by osama on 1/1/17.
 */

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

    private String dstAddress;
    private int dstPort;
    private String GPIOName;
    private Boolean isChecked=false;


    /*
    * @param addr IP Address of Raspberry PI Server
    * @param port port of Raspberry PI Server
    * @param GPIOName name of GPIO PIN to control
    * @param isChecked boolean to change the LED value
    */
    Client(String addr, int port,String GPIOName, Boolean isChecked) {
        this.dstAddress = addr;
        this.dstPort = port;
        this.isChecked = isChecked;
        this.GPIOName = GPIOName;
    }


    @Override
    public void run() {
        Socket socket = null;

        try {
            //Create a Connection Socket to given IP Address and Port
            socket = new Socket(dstAddress, dstPort);
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(
                            socket.getOutputStream())), true);
            // Send Command over opened socket
            out.println(GPIOName+" "+BooleantoString(isChecked));
            out.flush();


        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }



    //Change command Boolean On/Off to String
    private String BooleantoString(Boolean isChecked){
        if(isChecked){
            return "on";
        }
        return "off";
    }

}