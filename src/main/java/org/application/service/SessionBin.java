package org.application.service;

import lombok.Getter;
import lombok.Setter;
import org.application.container.ValuesContainer;
import org.application.entity.Column;
import org.application.security.MyUserPrincipal;
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


    @PostConstruct
    public void initColumns() throws Exception {
        String userName = myUserPrincipal.getUsername();

        Column toDoColumn =  columnService.getColumnByName(ValuesContainer.TO_DO_COLUMN_NAME);
        Column inProgressColumn =  columnService.getColumnByName(ValuesContainer.IN_PROGRESS_COLUMN_NAME);
        Column doneColumn =  columnService.getColumnByName(ValuesContainer.DONE_COLUMN_NAME);



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

		}
    }
}