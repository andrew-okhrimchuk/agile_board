package org.disturbed75.application.DAO;

import org.disturbed75.application.entity.Ticket;

import java.util.List;


public interface TicketDAO {

    boolean addNewTicket(String columnName, String ticketName, String ticketDescription);
    void deleteTicket(String columnName, String ticketName);
    void editTicket(String oldName, String newName, String columnName, String description);
    List<Ticket> getTicketsFromAllColumns();
    void moveTicket(String name, String description, String column, String newColumn);
}
