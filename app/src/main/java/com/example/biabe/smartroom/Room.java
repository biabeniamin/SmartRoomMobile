package com.example.biabe.smartroom;

import java.util.Arrays;

public class Room {
    private TcpClient tcp;
    private static Room room = new Room();
    private Room()
    {
         tcp = new TcpClient();
    }

    public static Room GetInstance()
    {
        return room;
    }


    public void Alert(int priority)
    {
        //from 0 to 4. 4 is the most important

        tcp.SendMessage(Arrays.asList(12,priority,0));
    }

    public void StopMusic()
    {
        //from 0 to 4. 4 is the most important

        tcp.SendMessage(Arrays.asList(10,0,0));
    }
}
