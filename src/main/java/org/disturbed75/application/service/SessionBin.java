package org.disturbed75.application.service;

import lombok.Getter;
import lombok.Setter;
import org.disturbed75.application.container.ValuesContainer;
import org.disturbed75.application.entity.Column;
import org.disturbed75.application.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Component()
@Scope(value = "session")
@Setter
@Getter
public class SessionBin {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private ColumnService columnService;
    private MyUserPrincipal myUserPrincipal = MyUserPrincipal.safeGet();

    {System.out.println("Make by SessionBin");}

    @PostConstruct
    public void initColumns() throws Exception {
        Column toDoColumn =  columnService.getColumnByName(ValuesContainer.TO_DO_COLUMN_NAME);
        Column inProgressColumn =  columnService.getColumnByName(ValuesContainer.IN_PROGRESS_COLUMN_NAME);
        Column doneColumn =  columnService.getColumnByName(ValuesContainer.DONE_COLUMN_NAME);
        String userName = myUserPrincipal.getUsername();


         if(    toDoColumn == null ||
                 inProgressColumn  == null  ||
                 doneColumn  == null )
         {
             toDoColumn       = new Column(ValuesContainer.TO_DO_COLUMN_NAME);
             inProgressColumn = new Column(ValuesContainer.IN_PROGRESS_COLUMN_NAME);
             doneColumn       = new Column(ValuesContainer.DONE_COLUMN_NAME);

             toDoColumn.setUsername(userName);
             inProgressColumn.setUsername(userName);
             doneColumn.setUsername(userName);

             toDoColumn.setTickets(new ArrayList<>());
             inProgressColumn.setTickets(new ArrayList<>());
             doneColumn.setTickets(new ArrayList<>());

             columnService.addNewColumn(toDoColumn);
             columnService.addNewColumn(inProgressColumn);
             columnService.addNewColumn(doneColumn);

             System.out.println(toDoColumn);
             System.out.println(inProgressColumn);
             System.out.println(doneColumn);
		}
    }
}