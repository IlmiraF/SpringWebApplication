package ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain.User;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.repos.UserRepo;

import javax.transaction.Transactional;

@Controller
public class UserEditController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/profile/{username}/edit_page")
    public String edit(@PathVariable("username") String userName, @AuthenticationPrincipal User currentUser, Model model) {
        User user = userRepo.findByUsername(userName);
        if(user != null) {
            model.addAttribute("usr", user);
        }
        model.addAttribute("logged", currentUser);
        return "userEdit";
    }

    @PostMapping("/profile/{username}/edit_page")
    public String editUser(@PathVariable("username") String userName, @AuthenticationPrincipal User currentUser, Model model, User newUser) {
        User user1 = userRepo.findByUsername(userName);
        if(user1 != null) {
            model.addAttribute("usr", user1);
        }
        model.addAttribute("logged", currentUser);
        User user = userRepo.findByUsername(currentUser.getUsername());

        if(user != null) {
            user.setPassword(newUser.getPassword());
            user.setEmail(newUser.getEmail());
            userRepo.save(user);
        }

        return "redirect:/logout";
    }

    @Transactional
    @GetMapping("/profile/{username}/delete")
    public String deleteUser(@PathVariable("username") String userName, @AuthenticationPrincipal User currentUser, Model model) {
        User user1 = userRepo.findByUsername(userName);
        if(user1 != null) {
            model.addAttribute("usr", user1);
        }
        model.addAttribute("logged", currentUser);
        User user = userRepo.findByUsername(currentUser.getUsername());

        if(user != null) {
            userRepo.deleteById(user.getId());
        }

        return "redirect:/logout";
    }
}
