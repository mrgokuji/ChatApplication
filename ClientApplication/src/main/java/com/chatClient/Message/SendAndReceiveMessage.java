package com.chatClient.Message;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SendAndReceiveMessage {
    protected Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream outputStream;
    private Scanner in;
    public SendAndReceiveMessage(String userName, String receiverUserName){
        try {
            socket = new Socket("localhost",9999);
            dataInputStream = new DataInputStream(socket.getInputStream());
            outputStream = new DataOutputStream(socket.getOutputStream());

            outputStream.writeUTF(userName);
            outputStream.flush();

            outputStream.writeUTF(receiverUserName);
            outputStream.flush();

            in = new Scanner(System.in);
            new Thread(()-> readMessages()).start();
            new Thread(()-> writeMessage()).start();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    private void readMessages(){
        String line = "";
        try {
            while (!line.equals("##")) {
                line = dataInputStream.readUTF();
                System.out.println(line);
            }
            inputClose();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void inputClose() throws IOException{
        dataInputStream.close();
    }

    private void writeMessage() {
        try {
            String line = "";
            while (!line.equals("##")) {
                line = in.nextLine();
                outputStream.writeUTF(line);
                outputStream.flush();
            }
            outputClose();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void outputClose() throws IOException {
        socket.close();
        outputStream. close();
        in.close();
    }

}
