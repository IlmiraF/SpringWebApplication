package ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain.User;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.service.UserService;

import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if(!userService.addUser(user)) {
            model.put("message", "User exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
