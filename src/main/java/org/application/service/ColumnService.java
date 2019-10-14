package org.application.service;

import org.application.entity.Column;
import org.application.security.MyUserPrincipal;
import org.application.DAO.ColumnDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColumnService  {

    @Autowired
    private ColumnDAO columnDAO;


    public void addNewColumn(Column columnName){
        columnDAO.save(columnName);
    }

    public Column getColumnByName(String columnName){
        final String username = MyUserPrincipal.get().getUsername();
        return getColumnByName(columnName, username);
    }

    public Column getColumnByName(String columnName, String username){
        Column lists =  columnDAO.getColumnByNameAndUsernameEquals(columnName, username);
        if (lists == null){
            return null;}
        return lists;
    }

    public void deliteColumnByName(String columnName){
        columnDAO.delete(columnName);
    }

    public List<Column> getAllColumns(){
        final String username = MyUserPrincipal.get().getUsername();
        return getAllColumns(username);
    }

    public List<Column> getAllColumns(String username){
        return columnDAO.getColumnByUsername(username);
    }

}
