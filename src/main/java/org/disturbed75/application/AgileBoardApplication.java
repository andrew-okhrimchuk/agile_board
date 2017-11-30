package org.disturbed75.application;


import org.disturbed75.application.container.ValuesContainer;
import org.disturbed75.application.entity.Column;
import org.disturbed75.application.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication(scanBasePackages ={"org.disturbed75.application.service","org.disturbed75.application.controller"})
@EnableAutoConfiguration
public class AgileBoardApplication implements CommandLineRunner {

	private final Column toDoColumn  = new Column(ValuesContainer.TO_DO_COLUMN_NAME);
	private final Column inProgressColumn = new Column(ValuesContainer.IN_PROGRESS_COLUMN_NAME);
	private final Column doneColumn = new Column(ValuesContainer.DONE_COLUMN_NAME);

	@Autowired
	private ColumnService columnService;

	public static void main(String[] args) {
		SpringApplication.run(AgileBoardApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		if(columnService.getColumnByName(ValuesContainer.TO_DO_COLUMN_NAME) != null ||
				columnService.getColumnByName(ValuesContainer.IN_PROGRESS_COLUMN_NAME) != null ||
				columnService.getColumnByName(ValuesContainer.DONE_COLUMN_NAME) != null){
			return;

		}
		toDoColumn.setTickets(new ArrayList<>());
		inProgressColumn.setTickets(new ArrayList<>());
		doneColumn.setTickets(new ArrayList<>());

		columnService.addNewColumn(toDoColumn);
		columnService.addNewColumn(inProgressColumn);
		columnService.addNewColumn(doneColumn);





}
}
