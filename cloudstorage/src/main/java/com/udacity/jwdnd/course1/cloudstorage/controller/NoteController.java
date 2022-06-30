package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/")
public class NoteController {
    private final UserService userService;
    private final NoteService noteService;

    @PostMapping("/note/add")
    public String createAndUpdateNote(Authentication authentication, Model model, Note note){
        //Authenticate userId
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserid();
        log.info("I AM HERE 1");

        Integer noteId = note.getNoteId();
        Note newNote = noteService.findById(noteId);

        if (newNote == null){
            try {
                noteService.createNote(note, userId);
                log.info("I AM HERE 2");
                model.addAttribute("isSuccessful", true);
                model.addAttribute("successMessage", "Note has been successfully created!");
            } catch (Exception e){
                model.addAttribute("hasError", true);
                model.addAttribute("errorMessage", "Note failed to create!");
            }
        } else {
            try {
                log.info("I AM HERE 3");
                noteService.upDateNote(note);
                model.addAttribute("isSuccessful", true);
                model.addAttribute("successMessage", "Note " + newNote.getNoteTitle() + " has been successfully updated!");
            } catch (Exception e){
                model.addAttribute("hasError", true);
                model.addAttribute("errorMessage", "Note failed to update!");
            }
        }

            return "result";
    }

    @GetMapping("/note/{noteId}/delete")
    public String deleteNote(@PathVariable Integer noteId, Model model) throws Exception {
        try {
            noteService.deleteNote(noteId);
            model.addAttribute("isSuccessful", true);
            model.addAttribute("successMessage", "Note has been successfully deleted!");
        } catch (Exception e){
            model.addAttribute("hasError", true);
            model.addAttribute("errorMessage", "Note deletion fail!");
        }
        return "result";
    }

}
