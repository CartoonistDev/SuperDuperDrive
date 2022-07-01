package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class HomeController {

    private final NoteService noteService;
    private final UserService userService;
    private final FileUploadService fileUploadService;
    private final EncryptionService encryptionService;
    private final CredentialService credentialService;
    //@RequiredArgsConstructor creates the required constructor on the fly


    @GetMapping("/home")
    public String homePage(Model model, Authentication authentication){
        log.info("I AM HERE 1");
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        log.info("I AM HERE 2");
        Integer userId = userService.getUserId(user);
        //Injecting all services so it will show in the home view
        model.addAttribute("userNotes", noteService.getUserNotes(userId));
        model.addAttribute("uploadedFiles", fileUploadService.getUserFiles(userId));
        model.addAttribute("credentials", credentialService.getAllCredentials(userId));
        model.addAttribute("encryptionService", encryptionService);

        return "home";
    }

    @PostMapping("/logout")
    public String handleLogout(Model model){
        model.addAttribute("isSuccessful", true);

        return "login";
    }
}