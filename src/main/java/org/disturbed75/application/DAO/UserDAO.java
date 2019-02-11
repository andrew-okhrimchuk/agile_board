package org.disturbed75.application.DAO;

import org.disturbed75.application.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDAO extends MongoRepository<User, Long> {

        User findByUsername(String username);
}
