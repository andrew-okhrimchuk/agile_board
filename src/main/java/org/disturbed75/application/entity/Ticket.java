package org.disturbed75.application.entity;

import lombok.*;

@Getter
@Setter
@ToString
public class Ticket {

    private String name;
    private String description;
    private String user;


    public Ticket() {
    }

    public Ticket(String name, String description) {
        this.name = name;
        this.description = description;
    }


}
