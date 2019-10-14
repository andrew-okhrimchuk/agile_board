package org.application.service;

import org.application.TestApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

//@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {TestApplication.class, /*CustomAuthenticationProvider.class, WebSecurityConfig.class*/}, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT )
@ContextConfiguration
public class AgileBoardApplicationTestRoot {

    @Autowired
    ApplicationContext context;

    @Test
    public void main() throws Exception {
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
    }


}