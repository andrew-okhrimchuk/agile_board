package org.disturbed75.application.entity;


import lombok.*;

@Getter
@Setter
@ToString
public class User {

    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }
}
