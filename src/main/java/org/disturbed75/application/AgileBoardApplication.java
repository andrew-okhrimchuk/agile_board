package org.disturbed75.application;


import org.disturbed75.application.DAO.ColumnDAO;
import org.disturbed75.application.entity.Column;
import org.disturbed75.application.entity.Ticket;
import org.disturbed75.application.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages ={"org.disturbed75.application.service","org.disturbed75.application.controller"})
@EnableAutoConfiguration
public class AgileBoardApplication implements CommandLineRunner {

	private final Column toDoColumn  = new Column("TO DO");
	private final Column inProgressColumn = new Column("In Progress");
	private final Column doneColumn = new Column("Done");

	@Autowired
	private ColumnService columnService;

	public static void main(String[] args) {
		SpringApplication.run(AgileBoardApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		toDoColumn.setTickets(new ArrayList<>());
		inProgressColumn.setTickets(new ArrayList<>());
		doneColumn.setTickets(new ArrayList<>());

		columnService.addNewColumn(toDoColumn);
		columnService.addNewColumn(inProgressColumn);
		columnService.addNewColumn(doneColumn);





}
}
