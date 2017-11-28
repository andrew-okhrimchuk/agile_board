package org.disturbed75.application.controller;

import org.disturbed75.application.entity.Ticket;
import org.disturbed75.application.service.ColumnService;
import org.disturbed75.application.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Disturbed on 11/28/2017.
 */
@Controller
public class MainPageController {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping(path = "/greeting", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("toDoColumn", columnService.getAllColumns().get(0));
        model.addAttribute("inProcessColumn", columnService.getAllColumns().get(1));
        model.addAttribute("doneColumn", columnService.getAllColumns().get(2));
        return "mainpage";
    }

    @RequestMapping(path = "/addticket", method = RequestMethod.POST)
    public @ResponseBody Ticket addNewTicket(@RequestParam String columnName, @RequestParam String name,
                        @RequestParam String description){
        System.out.println("here's column name " + columnName);

        return ticketService.addNewTicket(columnName,name,description);
    }
}
