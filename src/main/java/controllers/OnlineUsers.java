package controllers;

import controllers.dao.User;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

public class OnlineUsers {
    private static OnlineUsers instance = new OnlineUsers();
    private OnlineUsers(){}
    public static OnlineUsers getInstance(){
        return instance;
    }

    List<User> list = new ArrayList<>();

    public List<User> append (User user){
        list.add(user);
        return list;
    }
}
