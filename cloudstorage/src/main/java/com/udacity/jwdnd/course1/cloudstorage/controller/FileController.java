package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileUploadService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
//using thymeleaf to tell the browser where to look to getMapping
@RequiredArgsConstructor
@RequestMapping("/files")
public class FileController {
    private final UserService userService;
    private final FileUploadService fileUploadService;

    @PostMapping("/save")
    public String handleFileUpload(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication, File file, Model model) throws Exception {

        //Set a user object
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserid();
        file.setUserId(userId);

        if (userService.isUsernameAvailable(user))

        model.addAttribute("uploadedFiles", fileUploadService.getAllFiles());
        model.addAttribute("isSuccessful", true);
        model.addAttribute("successMessage", "File has been successfully uploaded!");

        //Upload the file
        this.fileUploadService.uploadFile(file, multipartFile);
        model.addAttribute("uploadedFiles", fileUploadService.getAllFiles());

        return "result";
    }

    @GetMapping("{fileId}/delete")
    public String deleteFile(@PathVariable Integer fileId, Model model) throws Exception {
        try {
            fileUploadService.delete(fileId);
            model.addAttribute("isSuccessful", true);
            model.addAttribute("successMessage", "File has been successfully deleted!");
        } catch (Exception e){
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", "File deletion fail!");
        }
        return "result";
    }

    @RequestMapping(value = "{fileId}/view", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Integer fileId, Authentication authentication){
        try {
            File file = fileUploadService.findById(fileId);
            ByteArrayResource resource = new ByteArrayResource(file.getFileData());
            MediaType data = MediaType.parseMediaType(file.getContentType());

            return ResponseEntity.ok()
                    .contentType(data)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFileName() + "\"")
                    .body(resource);
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}