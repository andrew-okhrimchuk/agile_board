package org.application.controller;

import org.application.DAO.TicketDAO;
import org.application.service.ColumnService;
import org.application.service.ServiceBin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//@RequestMapping("/agile_board")
@Controller
public class TicketController {

    @Autowired
    private ColumnService columnService;
    @Autowired
    private TicketDAO ticketDAO;
    @Autowired
    private ServiceBin serviceBin;

    @RequestMapping(path = "/greeting", method = RequestMethod.GET)
    public String index(Model model){
        serviceBin.getBin();

        model.addAttribute("toDoColumn", columnService.getAllColumns().get(0));
        model.addAttribute("inProcessColumn", columnService.getAllColumns().get(1));
        model.addAttribute("doneColumn", columnService.getAllColumns().get(2));

        model.addAttribute("toDoColumnTickets", columnService.getColumnByName("TO DO").getTickets());
        model.addAttribute("inProgressColumnTickets", columnService.getColumnByName("In Progress").getTickets());
        model.addAttribute("doneColumnTickets", columnService.getColumnByName("Done").getTickets());
        return "mainpage";
    }

    @RequestMapping(path = "/addticket", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<?> addNewTicket(@RequestParam String columnName, @RequestParam String name,
                                @RequestParam String description){
        return ResponseEntity.ok(ticketDAO.addNewTicket(columnName,name,description));

    }

    @RequestMapping(path = "/deleteticket", method = RequestMethod.POST)
    public  String deleteTicket(@RequestParam String name, @RequestParam String columnName){
        ticketDAO.deleteTicket(columnName, name);
       return "redirect:/greeting";
    }

    @RequestMapping(path = "/editticket", method = RequestMethod.POST)
    public String editTicket(@RequestParam String name,
                                           @RequestParam String oldName,
                                           @RequestParam String description,
                                           @RequestParam String columnName){
        ticketDAO.editTicket(oldName,name,columnName,description);
       return  "redirect:/greeting";
    }

    @RequestMapping(path = "/moveticket", method = RequestMethod.POST)
    public String moveTicket(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam String columnName,
                             @RequestParam String newColumn){
        ticketDAO.moveTicket(name,description, columnName,newColumn);
        return  "redirect:/greeting";
    }


}
