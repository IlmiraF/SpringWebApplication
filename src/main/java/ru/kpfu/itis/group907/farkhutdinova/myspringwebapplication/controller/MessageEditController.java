package ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain.Message;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.domain.User;
import ru.kpfu.itis.group907.farkhutdinova.myspringwebapplication.repos.MessageRepo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MessageEditController {
    @Autowired
    private MessageRepo messageRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/edit_message/{messageId}")
    public String editMessage(@PathVariable("messageId") Integer messageId, @AuthenticationPrincipal User currentUser, Model model) {
        Message message = messageRepo.findById(messageId);
        if(message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("logged", currentUser);
        return "messageEdit";
    }

    @PostMapping("/edit_message/{messageId}")
    public String doMessage(@PathVariable("messageId") Integer messageId, Message currentMessage, @RequestParam("file") MultipartFile file) throws IOException {
        Message message = messageRepo.findById(messageId);
        if(message != null) {
            message.setText(currentMessage.getText());
            message.setTag(currentMessage.getTag());
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                message.setFilename(resultFilename);
            }
            messageRepo.save(message);
        }
        return "redirect:/main";
    }



}
