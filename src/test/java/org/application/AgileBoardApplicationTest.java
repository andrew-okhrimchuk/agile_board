package org.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AgileBoardApplication.class}, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT )
public class AgileBoardApplicationTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void main() throws Exception {
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
    }

    @PostConstruct
    public void beansname() throws Exception {
        System.out.println("Bean definition names: " + Arrays.toString(context.getBeanDefinitionNames()));
    }

    //@Test
    public void run() throws Exception {
    }

}