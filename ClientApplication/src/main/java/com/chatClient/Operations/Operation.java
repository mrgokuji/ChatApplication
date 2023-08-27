package com.chatClient.Operations;

import com.chatClient.Model.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public abstract class Operation {
    HttpRequest request = null;
    String operationType ;

    public String getOperationType() {
        return operationType;
    }


    abstract void performOperation(User user) throws IOException, InterruptedException, URISyntaxException;
}
