package ua.com.jcoh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.jcoh.entity.User;
import ua.com.jcoh.service.MailService;
import ua.com.jcoh.service.UserService;
import ua.com.jcoh.validator.UserValidator;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;


@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    MailService mailService;

    @RequestMapping("/")
    public String toMainPage(Principal principal, Model model){
        model.addAttribute("principal", principal);
        return "index";
    }

    @RequestMapping(value = "registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("eUser",new User());
        return "registration";
    }

    @RequestMapping(value = "registration", method = RequestMethod.POST)
    public String registrationOfUser(@ModelAttribute("eUser") @Valid User user, BindingResult result) throws IOException {
        if (result.hasErrors()){
            return "registration";
        }
        userService.save(user);
        mailService.send(user);
        return "redirect:/welcome";
    }

    @RequestMapping(value = "welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }

    @InitBinder
    public void bind(WebDataBinder binder){
        binder.addValidators(userValidator);
    }

}
