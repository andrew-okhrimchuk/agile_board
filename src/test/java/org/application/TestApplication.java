package org.application;

import org.application.entity.User;
import org.application.service.MyUserDetailsService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = {
        "org.application.config",
        "org.application.controller",
        "org.application.security",
        "org.application.service"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
                        value = {/*AgileBoardApplication.class, WebSecurityConfig.class*/})})
public class TestApplication implements CommandLineRunner {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    ApplicationContext context;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TestApplication.class, args);
    }

    public void run(String... strings) {
        User user = this.myUserDetailsService.findByUsername("user4");
        if (user == null) {
            user = new User();
            user.setUsername("user4");
            user.setPassword("123");
            System.out.println(user.getUsername());
            this.myUserDetailsService.save(user);
        }
        else System.out.println(user.getUsername() + "Test-Test-Test-Test-Test-Test-Test-Test-Test");

    }




    @PostConstruct
    public void beansname() throws Exception {
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
    }
}
