package org.disturbed75.application.DAO;

import org.disturbed75.application.entity.Column;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ColumnDAO extends MongoRepository<Column, String> {

    List<Column> getColumnByUsername(String name);
    Column getColumnByNameAndUsernameEquals(String name, String username);
}
