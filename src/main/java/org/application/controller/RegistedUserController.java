package org.application.controller;

import org.application.entity.User;
import org.application.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;



//@RequestMapping("/agile_board")
@Controller
public class RegistedUserController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;


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

        User existing = myUserDetailsService.findByUsername(user.getUsername());
        if (existing != null){
            result.rejectValue("username", null, "There is already an account registered with that username");
        }

        if (result.hasErrors()){
            return "registration";
        }
        myUserDetailsService.save(user);
        return "redirect:/registration?success";
    }
}
