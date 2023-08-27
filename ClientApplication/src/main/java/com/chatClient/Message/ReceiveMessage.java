package com.chatClient.Message;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveMessage {
    private Socket socket;
    private DataInputStream dataInputStream;

    ReceiveMessage(){
        try {
        socket = new Socket("localhost",9999);
        dataInputStream = new DataInputStream(socket.getInputStream());
        readMessages();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void readMessages() throws IOException {
        String line = "";
        while (line!="Done"){
            line = dataInputStream.readUTF();
            System.out.println(line);
        }
        close();
    }

    private void close() throws IOException{
        socket.close();
        dataInputStream.close();
    }
}
