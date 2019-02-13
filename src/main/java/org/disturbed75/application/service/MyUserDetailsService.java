package org.disturbed75.application.service;

import org.disturbed75.application.DAO.UserDAO;
import org.disturbed75.application.entity.User;
import org.disturbed75.application.security.MyUserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDAO userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        System.out.println("username = [" + username + "]");

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        System.out.println("user.getUsername() = [" + user.getUsername() + "]" + "user.getPassword = [" + user.getPassword() + "]");
        MyUserPrincipal test = new MyUserPrincipal(user);
        System.out.println(test.getAuthorities()+" - " + test.getUsername() + " - " + test.getPassword());
        return test;
    }
}
