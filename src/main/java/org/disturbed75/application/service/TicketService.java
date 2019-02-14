package org.disturbed75.application.service;

import org.disturbed75.application.DAO.ColumnDAO;
import org.disturbed75.application.DAO.TicketDAO;
import org.disturbed75.application.container.ValuesContainer;
import org.disturbed75.application.entity.Column;
import org.disturbed75.application.entity.Ticket;
import org.disturbed75.application.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class TicketService implements TicketDAO {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private ColumnDAO columnDAO;

    @Override
    public boolean addNewTicket(String columnName, String ticketName, String ticketDescription){
        List<Ticket> allTickets = getTicketsFromAllColumns();
        for(Ticket ticket : allTickets){
            if(ticketName.equals(ticket.getName())){
                return false;
            }
        }
        Column column = columnService.getColumnByName(columnName);
        Ticket ticket = new Ticket(ticketName, ticketDescription);
        column.getTickets().add(ticket);
        columnDAO.save(column);
        return true;
    }

    @Override
    public void deleteTicket(String columnName, String ticketName){
        Column column  = columnService.getColumnByName(columnName);
        for(Ticket ticket: column.getTickets()){
            if(ticket.getName().equals(ticketName)){
                column.getTickets().remove(ticket);
                break;
            }
        }

        columnDAO.save(column);
    }

    @Override
    public void editTicket(String oldName, String newName, String columnName, String description){
        Column column  = columnService.getColumnByName(columnName);
        int counter = 0;
        for(int i = 0; i< column.getTickets().size(); i++){
            if(column.getTickets().get(i).getName().equals(oldName)){
                counter = i;
                break;
            }
        }
        column.getTickets().get(counter).setName(newName);
        column.getTickets().get(counter).setDescription(description);
        columnDAO.save(column);
    }

    @Override
    public List<Ticket> getTicketsFromAllColumns(){
        List<Ticket> allTickets = new ArrayList<>();
        List<Ticket> toDoTickets = columnService.getColumnByName(ValuesContainer.TO_DO_COLUMN_NAME).getTickets();
        List<Ticket> inProgressTickets = columnService.getColumnByName(ValuesContainer.IN_PROGRESS_COLUMN_NAME).getTickets();
        List<Ticket> doneTickets = columnService.getColumnByName(ValuesContainer.DONE_COLUMN_NAME).getTickets();
        allTickets.addAll(0, toDoTickets);
        allTickets.addAll(0, inProgressTickets);
        allTickets.addAll(0, doneTickets);
        return allTickets;
    }

    @Override
    public void moveTicket(String name, String description, String column, String newColumn) {
            deleteTicket(column,name);
            Column column1 = columnService.getColumnByName(newColumn);
            column1.getTickets().add(new Ticket(name, description));
            columnDAO.save(column1);
    }


    public void start(){
        final String username = MyUserPrincipal.get().getUsername();

        Column toDoColumnName =  columnService.getColumnByName(ValuesContainer.TO_DO_COLUMN_NAME);
        Column inProgressColumnName =  columnService.getColumnByName(ValuesContainer.IN_PROGRESS_COLUMN_NAME);
        Column doneColumnName =  columnService.getColumnByName(ValuesContainer.DONE_COLUMN_NAME);
        System.out.println("toDoColumnName = " + toDoColumnName);
        System.out.println("inProgressColumnName = " + inProgressColumnName);
        System.out.println("doneColumnName = " + doneColumnName);

       /* if(    toDoColumnName.getUsername().equalsIgnoreCase("admin")||
                inProgressColumnName.getUsername().equalsIgnoreCase("admin")  ||
                doneColumnName.getUsername().equalsIgnoreCase("admin")){
            return;
		}*/
        final Column toDoColumn  = new Column(ValuesContainer.TO_DO_COLUMN_NAME);
        final Column inProgressColumn = new Column(ValuesContainer.IN_PROGRESS_COLUMN_NAME);
        final Column doneColumn = new Column(ValuesContainer.DONE_COLUMN_NAME);

        toDoColumn.setTickets(new ArrayList<>());
        toDoColumn.setUsername(username);

        inProgressColumn.setTickets(new ArrayList<>());
        inProgressColumn.setUsername(username);

        doneColumn.setTickets(new ArrayList<>());
        doneColumn.setUsername(username);

        columnService.addNewColumn(toDoColumn);
        columnService.addNewColumn(inProgressColumn);
        columnService.addNewColumn(doneColumn);
    }



}
