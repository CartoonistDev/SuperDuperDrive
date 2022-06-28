package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
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
//@RequiredArgsConstructor
@RequestMapping("/home")
public class HomeController {

    private final NoteService noteService;
    private final UserService userService;
    private final FileUploadService fileUploadService;
    //@RequiredArgsConstructor creates the required constructor on the fly


    public HomeController(NoteService noteService, UserService userService, FileUploadService fileUploadService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping
    public String homePage(Model model, Authentication authentication){
        log.info("I AM HERE 1");
        authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication.getName();
        log.info("I AM HERE 2");
        Integer userId = userService.getUserId(user);
        model.addAttribute("userNotes", noteService.getUserNotes(userId));
        model.addAttribute("uploadedFiles", fileUploadService.getUserFiles(userId));
//        User user = userService.getUser(authentication.getName());
//        model.addAttribute("userNotes", noteService.getUserNotes(user.getUserid()));
        return "home";
    }

    @PostMapping("/logout")
    public String handleLogout(Model model){
        model.addAttribute("isSuccessful", true);
        return "login";
    }
}