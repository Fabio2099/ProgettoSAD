package com.g2.Model;

public class Player {
    private String name;
    private String username;
    private String password;

    private static Player instance;

    public Player(String name) {
        this.name = name;
    }

    /*  public static Player getInstance() {
        if (instance == null) {
            synchronized (Player.class) {
                if (instance == null) {
                    instance = new Player();
                }
            }
        }
        return instance;
    }*/

    public String getName(){
        return name;
    }
    public void setName(){
        this.name=name;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
