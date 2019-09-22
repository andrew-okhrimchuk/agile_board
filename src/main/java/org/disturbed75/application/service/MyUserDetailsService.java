package org.disturbed75.application.service;

import org.disturbed75.application.DAO.UserDAO;
import org.disturbed75.application.entity.User;
import org.disturbed75.application.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import static org.disturbed75.application.util.UserUtil.prepareToSave;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userDAO.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(prepareToSave(user, passwordEncoder));
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }

}
