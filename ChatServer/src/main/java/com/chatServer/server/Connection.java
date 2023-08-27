package com.chatServer.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Connection {
    private ServerSocket serverSocket;

    private static final int PORT = 9999;
    public static final String STOP_STRING = "##";
    public static Map<String,ConnectedClient> clientMap = new HashMap<>();

    public Connection(){
        try {
            System.out.println("Starting Server");
            serverSocket = new ServerSocket(PORT);
            while (true) initConnections();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initConnections() throws IOException {
        Socket clientSocket =  serverSocket.accept();
        System.out.println(clientSocket.getInetAddress());
        if(clientSocket.isConnected())
        new Thread(()->{
            ConnectedClient client = new ConnectedClient(clientSocket);
            client.readMessages();
        }).start();

    }

}
