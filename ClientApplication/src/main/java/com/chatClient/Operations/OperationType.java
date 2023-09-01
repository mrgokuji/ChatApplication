package com.chatClient.Operations;

import java.util.HashMap;

public enum OperationType {
    ADD_FRIEND("AddFriend"),
    DELETE_FRIEND("DeleteFriend"),
    LIST_FRIEND("ListFriend"),
    START_MESSAGING("START_MESSAGING");

    private final String name;
    OperationType(String type){
        name = type;
    }
    private static final HashMap<String, OperationType> map = new HashMap<>(values().length, 1);

    static {
        for (OperationType c : values()) map.put(c.name, c);
    }

    public static OperationType of(String name){
        return map.get(name);
    }

}
