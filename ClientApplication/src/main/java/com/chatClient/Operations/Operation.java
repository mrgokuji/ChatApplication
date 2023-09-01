package com.chatClient.Operations;

import com.chatClient.Model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public abstract class Operation {
    String operationType ;
    User currentUser;
    Operation(User user){
        currentUser = user;
    }

    public String getOperationType() {
        return operationType;
    }


    abstract void performOperation(User user) throws IOException, InterruptedException, URISyntaxException;
}
