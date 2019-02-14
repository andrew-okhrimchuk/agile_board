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

    public Column getColumnByName(String name){


        final String username = MyUserPrincipal.get().getUsername();
        List<Column> lists =  columnDAO.getColumnByName(name);
        if (lists == null){return null;}

        for (Column xx:lists)
        {System.out.println("xx = " + xx);        }

        for (Column list:lists) {
            if (list.getUsername().equals(username)) {
                return  list;
            }
        }
        return null;
    }

    public List<Column> getAllColumns(){
        final String username = MyUserPrincipal.get().getUsername();
        return columnDAO.getColumnByUsername(username);
    }

}
