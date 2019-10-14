package org.application.entity;


import lombok.*;

@Getter
@Setter
@ToString
public class User {
    private String username;
    private String password;


    public User() {    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
