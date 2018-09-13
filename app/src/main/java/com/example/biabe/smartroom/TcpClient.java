package com.example.biabe.smartroom;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TcpClient {


    public static final String TAG = TcpClient.class.getSimpleName();
    public static final String SERVER_IP = "192.168.0.108"; //server IP address
    public static final int SERVER_PORT = 10000;
    // message to send to the server
    private String mServerMessage;
    // while this is true, the server will continue running
    private boolean mRun = false;
    // used to send messages
    private PrintWriter mBufferOut;
    // used to read messages from the server
    private BufferedReader mBufferIn;
    private Socket socket;

    public TcpClient()
    {
        Runnable myTask = new Runnable() {
            @Override
            public void run() {
                Connect();
            }
        };


        Thread t = new Thread(myTask);
        t.start();

    }


    public void SendMessage(final List<Integer> message) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                try {
                    //here you must put your computer's IP address.
                    InetAddress serverAddr = InetAddress.getByName(SERVER_IP);

                    Log.d("TCP Client", "C: Connecting...");

                    //create a socket to make the connection with the server
                    socket = new Socket(serverAddr, SERVER_PORT);

                    try {

                        //sends the message to the server
                        mBufferOut = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                        //receives the message which the server sends back
                        mBufferIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                        if(3 > message.size())
                        {
                            Log.d(TAG, "Not enough parameters " + message);
                        }
                        mBufferOut.write(message.get(0));
                        mBufferOut.write(message.get(1));
                        mBufferOut.write(message.get(2));
                        mBufferOut.flush();

                        /*while (mRun) {
                            try {
                                mServerMessage = mBufferIn.readLine();

                                if (mServerMessage != null) {
                                    //call the method messageReceived from MyActivity class
                                    //mMessageListener.messageReceived(mServerMessage);
                                    int i = 5;
                                    i++;


                                }
                                break;
                            }
                            catch (IOException ee)
                            {

                            }

                        }*/


                        //in this while the client listens for the messages sent by the server


                        Log.d("RESPONSE FROM SERVER", "S: Received Message: '" + mServerMessage + "'");

                    } catch (Exception e) {
                        Log.e("TCP", "S: Error", e);
                    } finally {
                        //the socket must be closed. It is not possible to reconnect to this socket
                        // after it is closed, which means a new socket instance has to be created.
                        mBufferIn.close();
                        mBufferOut.close();
                        socket.close();
                    }

                } catch (Exception e) {
                    Log.e("TCP", "C: Error", e);
                }


            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    private void Connect() {

        mRun = true;



    }

}
