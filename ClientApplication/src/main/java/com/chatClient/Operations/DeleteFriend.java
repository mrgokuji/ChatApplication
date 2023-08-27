package com.chatClient.Operations;

import com.chatClient.Model.User;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteFriend extends Operation{

    DeleteFriend(){
        operationType = "DeleteFriend";
    }
    @Override
    void performOperation(User user) throws IOException, InterruptedException, URISyntaxException {
        request = HttpRequest.newBuilder()
                .uri(new URI("localhost:5600/addFriend?userId=%s".formatted(user.getUserId())))
                .DELETE()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Request Deleted with code "+response.statusCode());
    }
}
