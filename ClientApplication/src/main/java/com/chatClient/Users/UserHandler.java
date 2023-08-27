package com.chatClient.Users;

import com.chatClient.Model.User;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class UserHandler {

    String currentUserToken = "thisIsTokenForCurrentUser";

    List<User> getAllActiveUsers() throws URISyntaxException, IOException, InterruptedException {
        List<User> users = new ArrayList<>();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("localhost:5600/getAllUsers"))
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Got All the users  " + response.statusCode());
        return users;
    }

    int registerUser(User user) throws URISyntaxException, IOException, InterruptedException{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("localhost:5600/user"))
                .POST(HttpRequest.BodyPublishers.ofString(user.toString()))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> response = httpClient.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response);
        return response.statusCode();
    }

}
