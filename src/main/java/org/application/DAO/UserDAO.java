package org.application.DAO;

import org.application.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserDAO extends MongoRepository<User, Long> {

        User findByUsername(String username);

}
