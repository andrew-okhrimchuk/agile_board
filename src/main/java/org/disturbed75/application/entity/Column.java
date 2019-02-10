package org.disturbed75.application.entity;

import org.springframework.data.annotation.Id;

import java.util.List;


public class Column {

    @Id
    private String id;
    private String name;
    private List<Ticket> tickets;

    public Column() {
    }

    public Column(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
