package org.disturbed75.application.entity;

import org.springframework.data.annotation.Id;
import java.util.List;
import lombok.*;

@Getter
@Setter
@ToString
public class Column {

    @Id
    private String id;
    private String name;
    private List<Ticket> tickets;
    private String username;

    public Column() {
    }
    public Column(String name) {
        this.name = name;
    }
}
