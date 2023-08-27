package com.chatClient.Operations;

import com.chatClient.Model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class OperationHandler {
    static final Map<OperationType,Operation> operationMap = new HashMap<>();
    static {
        operationMap.put(OperationType.ADD_FRIEND, new AddFriend());
        operationMap.put(OperationType.DELETE_FRIEND, new DeleteFriend());
        operationMap.put(OperationType.LIST_FRIEND, null);
    }
    void doOperation(String op) throws IOException, URISyntaxException, InterruptedException {
        User user = null;
        OperationType type = OperationType.of(op);
        switch (type){
            case ADD_FRIEND, DELETE_FRIEND -> user = new User(); // GET user from List of Users.
            default ->{
                System.out.println("Invalid Operation");
                return;
            }
        }
        Operation operation = operationMap.get(type);
        operation.performOperation(user);

    }

}
