package com.chatClient.Operations;

import com.chatClient.Model.User;
import com.chatClient.Utils.EndPoints;
import com.chatClient.Utils.RequestBodyTemplate;
import com.chatClient.Utils.Utils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AddFriend extends Operation {
    AddFriend(User user)  {
        super(user);
        operationType = "AddFriend";
    }

    @Override
    void performOperation(User user) throws IOException, InterruptedException, URISyntaxException {
        Utils.makeRequest(EndPoints.addFriend,"POST", RequestBodyTemplate.addFriend.formatted(currentUser.getUserId(),user.getUserId()));
    }
}
