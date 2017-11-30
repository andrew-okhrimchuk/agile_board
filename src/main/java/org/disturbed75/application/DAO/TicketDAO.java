package org.disturbed75.application.DAO;

import org.disturbed75.application.entity.Ticket;

import java.util.List;

/**
 * Created by Disturbed on 11/30/2017.
 */
public interface TicketDAO {

    boolean addNewTicket(String columnName, String ticketName, String ticketDescription);
    void deleteTicket(String columnName, String ticketName);
    void editTicket(String oldName, String newName, String columnName, String description);
    List<Ticket> getTicketsFromAllColumns();
    void moveTicket(String name, String description, String column, String newColumn);
}
