package com.chatClient;

import com.chatClient.Model.User;
import com.chatClient.Operations.OperationHandler;

import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("WELCOME TO OUR NEW MESSENGER");
        System.out.println("PLEASE CREATE YOUR USER\nEnter username : ");
        String uname = input.nextLine();
        System.out.println("Enter userId : ");
        Integer uId = input.nextInt();
        input.next();
        User currentUser = new User(uId,uname);
        OperationHandler operationHandler = new OperationHandler(currentUser);
        String operation = "";
        while (true){
            try{
                operation = takeInput();
                if(operation.equals("FINISH")) break;
                operationHandler.doOperation(operation);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static String takeInput() {
        System.out.println("LIST OF OPERATION TO CHOOSE FROM ");
        System.out.println("AddFriend\nDeleteFriend\nListFriend\nSTART_MESSAGING\nFINISH");
        String command = input.nextLine();
        return command;
    }
}