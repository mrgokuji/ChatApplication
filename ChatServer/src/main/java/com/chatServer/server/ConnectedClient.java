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
            System.out.println( "Getting data from Port" + clientSocket.getPort());
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

    public void transferMessages() {
        try {

            ConnectedClient receiverClient = null;
            while(receiverClient==null){
                receiverClient = clientMap.getOrDefault(readChatsFrom,null);
                Thread.sleep(1000);
            }
            while (!line.equals(Connection.STOP_STRING)) {
                line = inputStream.readUTF(); // READ messages from client
                System.out.println(line);
                writeMessage(receiverClient.outputStream); // Write messages to receiver client
            }
            clientMap.remove(currentUserName);
            close();
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
