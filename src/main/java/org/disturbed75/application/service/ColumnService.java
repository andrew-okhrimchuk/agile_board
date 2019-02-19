package org.disturbed75.application.service;

import org.disturbed75.application.DAO.ColumnDAO;
import org.disturbed75.application.entity.Column;
import org.disturbed75.application.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService  {

    @Autowired
    private ColumnDAO columnDAO;

    public void addNewColumn(Column column){
        columnDAO.save(column);
    }


    public Column getColumnByName(String name){
        final String username = MyUserPrincipal.get().getUsername();
        Column lists =  columnDAO.getColumnByNameAndUsernameEquals(name, username);
        if (lists == null){
            return null;}
        return lists;
    }

    public List<Column> getAllColumns(){
        final String username = MyUserPrincipal.get().getUsername();
        return columnDAO.getColumnByUsername(username);
    }

}
