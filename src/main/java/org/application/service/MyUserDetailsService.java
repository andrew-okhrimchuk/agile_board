package org.application.service;

import org.application.entity.User;
import org.application.security.MyUserPrincipal;
import org.application.util.UserUtil;
import org.application.DAO.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        org.application.entity.User user = findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
    }
    public void save(org.application.entity.User user) {
        user.setPassword((UserUtil.prepareToSave(user, passwordEncoder)).getPassword());
        userDAO.save(user);
    }

    public User findByUsername(String username) {
        return userDAO.findByUsername(username);
    }


}
