package com.chatClient.Users;

import com.chatClient.Model.User;
import com.chatClient.Utils.EndPoints;
import com.chatClient.Utils.Utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class UserHandler {

    String currentUserToken = "thisIsTokenForCurrentUser";


    public List<User> getAllActiveUsers() throws IOException, ParseException {
        List<User> userList = new ArrayList<>();
        String body = Utils.makeRequest(EndPoints.listUser,"GET","").get();
        System.out.println(body);
        JSONArray object = (JSONArray) new JSONParser().parse(body);
        for(int i=0;i<object.size();i++){
            JSONObject jsonObject = (JSONObject) object.get(i);
            userList.add(new User(Integer.parseInt(jsonObject.get("userId").toString()),(String)jsonObject.get("userName")));
        }
        return userList;
    }

    public void registerUser(User user) throws IOException {
        System.out.println(Utils.makeRequest(EndPoints.registerUser,"POST", user.toString()).get());
    }

}
