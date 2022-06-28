package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class NoteController {
    private UserService userService;
    private NoteService noteService;

    public NoteController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/note/add")
    public String createNote(Authentication authentication, Model model, Note note){
        //Authenticate userId
        User user = userService.getUser(authentication.getName());
        Integer userId = user.getUserid();

        Integer noteId = note.getNoteId();
        Note newNote = noteService.findById(noteId);

        if (newNote == null){
            try {
                noteService.createNote(note, userId);
                model.addAttribute("isSuccessful", true);
                model.addAttribute("successMessage", "Note " + newNote.getNoteTitle() + " has been successfully created!");
            } catch (Exception e){
                model.addAttribute("hasError", true);
                model.addAttribute("errorMessage", "Note failed to create!");
            }
        } else {
            try {
                noteService.upDateNote(note, userId);
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
    public String deleteFile(@PathVariable Integer noteId, Model model) throws Exception {
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
