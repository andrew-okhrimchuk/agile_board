package org.disturbed75.application.service;

import org.disturbed75.application.DAO.ColumnDAO;
import org.disturbed75.application.entity.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Disturbed on 11/28/2017.
 */
@Service
public class ColumnService {

    @Autowired
    private ColumnDAO columnDAO;

    public void addNewColumn(Column column){
        columnDAO.save(column);
    }

    public Column getColumnByName(String name){
        return columnDAO.getColumnByName(name);
    }

    public List<Column> getAllColumns(){
        return columnDAO.findAll();
    }
}
