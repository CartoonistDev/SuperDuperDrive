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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.SecureRandom;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class CredentialController {
    private final CredentialService credentialService;
    private final UserService userService;
    private final EncryptionService encryptionService;

    @PostMapping("/credential/add")
    public String addAndUpdateCredentials(Authentication authentication, Model model, Credential credential){
        //Encrypting the password
        SecureRandom random = new SecureRandom();
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserid();
        String credentialId = credential.getKey();
        Credential newCredential = credentialService.getUserCredential(credentialId);
        byte[] key = new byte[16];
        random.nextBytes(key);

        if (newCredential == null){
            try {
                credential.setKey(random.toString());
                credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credentialId));
                credentialService.createCredential(credential);
                log.info("I AM HERE 1");
                model.addAttribute("isSuccessful", true);
                model.addAttribute("successMessage", "Credential has been successfully created!");
            } catch (Exception e){
                model.addAttribute("hasError", true);
                model.addAttribute("errorMessage", "Credential failed to create!");
                }
        } else {
            try {
                credential.setKey(random.toString());
                credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credentialId));
                credentialService.upDateCredential(credential);
                log.info("I AM HERE 2");
                model.addAttribute("isSuccessful", true);
                model.addAttribute("successMessage", "Credential has been successfully updated");
            } catch (Exception e){
                model.addAttribute("hasError", true);
                model.addAttribute("errorMessage", "Credential failed to update!");
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
