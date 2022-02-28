package controllers;

public class UserLoader {
    String name;
    int role;
    String ip;
    String userName;

    private static UserLoader instance = new UserLoader();
    private UserLoader(){}
    public static UserLoader getInstance(){
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
