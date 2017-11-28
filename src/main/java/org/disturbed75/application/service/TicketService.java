package org.disturbed75.application.service;

import org.disturbed75.application.DAO.ColumnDAO;
import org.disturbed75.application.entity.Column;
import org.disturbed75.application.entity.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Disturbed on 11/28/2017.
 */
@Service
public class TicketService {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private ColumnDAO columnDAO;

    public Ticket addNewTicket(String columnName, String ticketName, String ticketDescription){
        Column column = columnService.getColumnByName(columnName);
        Ticket ticket = new Ticket(ticketName, ticketDescription);
        column.getTickets().add(ticket);
        columnDAO.save(column);
        return ticket;
    }
}
