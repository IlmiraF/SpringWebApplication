package ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain.User;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.repos.UserRepo;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class ProfileController {
    @Autowired
    private UserRepo userRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/profile/{username}")
    public String account(@PathVariable("username") String username, Model model, @AuthenticationPrincipal User loggedUser) {
        User user = userRepo.findByUsername(username);
        model.addAttribute("logged", loggedUser.getUsername());
        if(user != null) {
            model.addAttribute("usr", user);
            return "account";
        } else {
            return "redirect:/main";
        }
    }

    @PostMapping("/profile/{username}")
    public String addFile(@PathVariable("username") String username, @RequestParam("file") MultipartFile file, Model model, @AuthenticationPrincipal User loggedUser) throws IOException {
        User user = userRepo.findByUsername(username);
        model.addAttribute("logged", loggedUser.getUsername());

        if(user != null) {
            if(file != null) {
                File uploadDir = new File(uploadPath);

                if(!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                if(file.getSize() != 0) {
                    String uuidFile = UUID.randomUUID().toString();
                    String resultFilename = uuidFile + "." + file.getOriginalFilename();

                    file.transferTo(new File(uploadPath + "/" + resultFilename));

                    user.setFilename(resultFilename);
                }
            }

            userRepo.save(user);
            model.addAttribute("usr", user);
        }
        return "account";
    }
}
