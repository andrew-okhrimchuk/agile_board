package org.disturbed75.application.controller;

import org.disturbed75.application.DAO.TicketDAO;
import org.disturbed75.application.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class MainPageController {

    @Autowired
    private ColumnService columnService;

    @Autowired
    private TicketDAO ticketService;



    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model){
        model.addAttribute("toDoColumn", columnService.getAllColumns().get(0));
        model.addAttribute("inProcessColumn", columnService.getAllColumns().get(1));
        model.addAttribute("doneColumn", columnService.getAllColumns().get(2));
        model.addAttribute("toDoColumnTickets", columnService.getColumnByName("TO DO").getTickets());
        model.addAttribute("inProgressColumnTickets", columnService.getColumnByName("In Progress").getTickets());
        model.addAttribute("doneColumnTickets", columnService.getColumnByName("Done").getTickets());
        return "mainpage";
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }


    @RequestMapping(path = "/addticket", method = RequestMethod.POST)
    public @ResponseBody String addNewTicket(@RequestParam String columnName, @RequestParam String name,
                        @RequestParam String description){
        boolean result = ticketService.addNewTicket(columnName,name,description);
        if(result == true){
            return "true";
        }else{
            return "false";
        }
    }


    @RequestMapping(path = "/deleteticket", method = RequestMethod.POST)
    public  String deleteTicket(@RequestParam String name, @RequestParam String columnName){
        ticketService.deleteTicket(columnName, name);
       return "redirect:/";
    }

    @RequestMapping(path = "/editticket", method = RequestMethod.POST)
    public String editTicket(@RequestParam String name,
                                           @RequestParam String oldName,
                                           @RequestParam String description,
                                           @RequestParam String columnName){
        ticketService.editTicket(oldName,name,columnName,description);
       return  "redirect:/";
    }

    @RequestMapping(path = "/moveticket", method = RequestMethod.POST)
    public String moveTicket(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String columnName,
                             @RequestParam String newColumn){
        ticketService.moveTicket(name,description, columnName,newColumn);
        return  "redirect:/";
    }



}
