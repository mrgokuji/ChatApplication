package com.chatClient.Operations;

import com.chatClient.Message.SendAndReceiveMessage;
import com.chatClient.Model.User;
import com.chatClient.Users.UserHandler;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class OperationHandler {
    static final Map<OperationType,Operation> operationMap = new HashMap<>();
    static Scanner input = new Scanner(System.in);
    final User currentUser ;

    public OperationHandler(User currentUser) {
        this.currentUser = currentUser;
        operationMap.put(OperationType.ADD_FRIEND, new AddFriend(currentUser));
        operationMap.put(OperationType.DELETE_FRIEND, new DeleteFriend(currentUser));
        operationMap.put(OperationType.LIST_FRIEND, null);
    }

        static {
            try {
                users = new UserHandler().getAllActiveUsers();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }

    static List<User> users;
    public void doOperation(String op) throws IOException, URISyntaxException, InterruptedException, ParseException {
        if(op.isEmpty()) return;
        User user = null;
        System.out.println("Performing --> "+op);
        OperationType type = OperationType.of(op);
        switch (type){
            case LIST_FRIEND -> {
                users = new UserHandler().getAllActiveUsers();
                System.out.println(users);
            }
            case ADD_FRIEND, DELETE_FRIEND -> {
                System.out.println("Select the userId");
                System.out.println(users);
                user = users.get(input.nextInt());
            }//new UserHandler().getAllActiveUsers().get(0); // GET user from List of Users.
            case START_MESSAGING -> {
                System.out.println("ENTER THE USERNAME OF PERSON YOU WANNA TALK TO : ");
                String friendUserName = input.nextLine();
                new SendAndReceiveMessage(this.currentUser.getUserName(), friendUserName);
            }
            default ->{
                System.out.println("Invalid Operation");
                return;
            }
        }
        Operation operation = operationMap.get(type);
        operation.performOperation(user);

    }

}
