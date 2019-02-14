package org.disturbed75.application;


import org.disturbed75.application.DAO.UserDAO;
import org.disturbed75.application.container.ValuesContainer;
import org.disturbed75.application.entity.Column;
import org.disturbed75.application.entity.User;
import org.disturbed75.application.service.ColumnService;
import org.disturbed75.application.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

@SpringBootApplication(scanBasePackages ={"org.disturbed75.application.service","org.disturbed75.application.controller", "org.disturbed75.application.security"})
@EnableAutoConfiguration
public class AgileBoardApplication implements CommandLineRunner {

	/*private final Column toDoColumn  = new Column(ValuesContainer.TO_DO_COLUMN_NAME);
	private final Column inProgressColumn = new Column(ValuesContainer.IN_PROGRESS_COLUMN_NAME);
	private final Column doneColumn = new Column(ValuesContainer.DONE_COLUMN_NAME);*/
	private final Column user = new Column(ValuesContainer.USER);

	@Autowired
	private ColumnService columnService;
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	public static void main(String[] args) {
		SpringApplication.run(AgileBoardApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {

		User user = myUserDetailsService.findByUsername("admin");

		if (user == null) {
			user = new User();
			user.setUsername("admin");
			user.setPassword("admin");
			myUserDetailsService.save(user);
		}
	}
}

