package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

//    @GetMapping
//    public String getCredentialList(Model model,@RequestParam("credential") Credential credential){
//        model.addAttribute("SavedCredentials", credentialService.getAllCredentials(credential.getUserid()));
//        return "home";
//    }
    @PostMapping("/credential/add")
    public String addAndUpdateCredentials(Credential credential, Authentication authentication, Model model){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserid();
        credential.setUserid(userId);
        String encodeKey = Base64.getEncoder().encodeToString(key);
        credential.setKey(encodeKey);
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));


        if (credential.getCredentialId() == null){
            try {
                credentialService.createCredential(credential, userId);
                log.info("I AM HERE 1");
                model.addAttribute("isSuccessful", true);
                model.addAttribute("successMessage", "Credential has been successfully added!");
                return "home";
            } catch (Exception e){
                model.addAttribute("hasError", true);
                model.addAttribute("errorMessage", "Credential failed to add, please try again.");
                }
        } else {
            try {
                credentialService.upDateCredential(credential);
                log.info("I AM HERE 2");
                model.addAttribute("isSuccessful", true);
                model.addAttribute("successMessage", "Credential has been successfully updated");
                return "home";
            } catch (Exception e){
                model.addAttribute("hasError", true);
                model.addAttribute("errorMessage", "Credential failed to update.");
            }
        }

        return "result";
    }

    @GetMapping("/credential/{credentialId}/delete")
    public String deleteCredential(@PathVariable Integer credentialId, Model model){
        try {
            credentialService.deleteCredential(credentialId);
            model.addAttribute("isSuccessful", true);
            model.addAttribute("successMessage", "Credential has been successfully deleted!");
        } catch (Exception e){
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", "Credential deletion fail!");
        }
        return "result";
    }
}
