package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {
    private final UserService userService;

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping()
    public String signupUser(@ModelAttribute User user, Model model) {

        if (userService.isUsernameAvailable(user)) {
            int enteredKeys = userService.createUser(user);
            if (enteredKeys < 0){
                model.addAttribute("signupError", "Please enter a valid username");
            } else {
                model.addAttribute("signupSuccess", true);
                model.addAttribute("signupSuccess", "Please enter your details to login");
                return "login";

            }
        } else if (!userService.isUsernameAvailable(user)){
            model.addAttribute("signupError", "Username already taken, Please enter a valid username");
        }
        return "signup";
    }
}
