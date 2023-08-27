package com.chatServer.server;

import java.io.*;
import java.net.Socket;

import static com.chatServer.server.Connection.clientMap;

public class ConnectedClient {
    public Socket clientSocket;
    public DataInputStream inputStream;
    public DataOutputStream outputStream;
    String line = "";
    String currentUserName = "";
    String readChatsFrom = "";

    public ConnectedClient(Socket clientSocket){
        this.clientSocket = clientSocket;

        try {
            this.inputStream = new DataInputStream(new BufferedInputStream(clientSocket.getInputStream())); //
            currentUserName = inputStream.readUTF(); // reading UserName
            System.out.println("User " + currentUserName + " is connected");

            clientMap.put(currentUserName,this);

            readChatsFrom = inputStream.readUTF(); // reading chats from
            System.out.println("Reading chats from User : " + readChatsFrom);

            this.outputStream = new DataOutputStream(new BufferedOutputStream(clientSocket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void close() throws IOException {
        inputStream.close();
        clientSocket.close();
        outputStream.close();
    }

    public void readMessages() {

        try {

            ConnectedClient out = null;
            while(out==null){
                out = clientMap.getOrDefault(readChatsFrom,null);
                Thread.sleep(1000);
            }
            while (!line.equals(Connection.STOP_STRING)) {
                line = inputStream.readUTF();
                System.out.println(line);
                writeMessage(out.outputStream);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void writeMessage(DataOutputStream out)  {
        try {
//        while(!line.equals(Connection.STOP_STRING)){
            out.writeUTF(currentUserName + " : " + line);
            out.flush();
//        }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
