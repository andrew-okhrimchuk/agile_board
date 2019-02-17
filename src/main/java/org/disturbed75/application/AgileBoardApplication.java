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


	@Autowired
	private MyUserDetailsService myUserDetailsService;
	@Autowired
	private ColumnService columnService;

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

		/*if(columnService.getColumnByName(ValuesContainer.TO_DO_COLUMN_NAME) == null ||
				columnService.getColumnByName(ValuesContainer.IN_PROGRESS_COLUMN_NAME) == null ||
				columnService.getColumnByName(ValuesContainer.DONE_COLUMN_NAME) == null)
		{
			new Column(ValuesContainer.TO_DO_COLUMN_NAME);
			new Column(ValuesContainer.IN_PROGRESS_COLUMN_NAME);
			new Column(ValuesContainer.DONE_COLUMN_NAME);
		}*/





	}
}

