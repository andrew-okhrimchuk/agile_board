package org.disturbed75.application.controller;

import org.disturbed75.application.DAO.TicketDAO;
import org.disturbed75.application.DAO.UserDAO;
import org.disturbed75.application.entity.User;
import org.disturbed75.application.service.ColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static org.disturbed75.application.util.UserUtil.prepareToSave;


@Controller
public class RegistedUserController {


    private UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("user")
    public User userRegistrationDto() {
        return new User();
    }

    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public String registerUserAccount(@ModelAttribute("user") User user,
                                      BindingResult result){

        User existing = userDAO.findByUsername(user.getUsername());
        if (existing != null){
            result.rejectValue("username", null, "There is already an account registered with that username");
        }

        if (result.hasErrors()){
            return "registration";
        }
     //   user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(prepareToSave(user, passwordEncoder));
        return "redirect:/registration?success";
    }
}
