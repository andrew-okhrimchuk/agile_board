package org.disturbed75.application.DAO;

import org.disturbed75.application.entity.Column;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ColumnDAO extends MongoRepository<Column, String> {

    Column getColumnByName(String name);
}
