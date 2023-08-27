package com.chatClient.Message;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SendMessages {
    private Socket socket;
    private DataOutputStream outputStream;
    private Scanner in;
    public SendMessages(){
        try {
            socket = new Socket("localhost",9999);
            outputStream = new DataOutputStream(socket.getOutputStream());
            in = new Scanner(System.in);
            writeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeMessage() throws IOException {
        String line = "";
        while(line!="Done"){
            line = in.nextLine();
            outputStream.writeUTF(line);
        }
        close();
    }

    private void close() throws IOException {
        socket.close();
        outputStream. close();
        in.close();
    }

}
