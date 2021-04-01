package ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain.User;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.repos.UserRepo;

@Controller
public class ProfileController {
    @Autowired
    private UserRepo userRepo;

    @RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
    public String account(@PathVariable("username") String username, Model model) {
        User user = userRepo.findByUsername(username);
        model.addAttribute("usr", user);
        return "account";
    }
}
