package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteMapper noteMapper;

    public void createNote(Note note, Integer userId){
        Note newNote = new Note(note.getNoteTitle(), note.getNoteDescription(), null, userId);
        noteMapper.addNote(newNote);
    }

    public void upDateNote(Note note){
        Note newNote = new Note(note.getNoteDescription(), note.getNoteTitle(), note.getNoteId(), note.getUserid());
        noteMapper.updateNote(newNote);
    }

    public void deleteNote(Integer noteId){
        noteMapper.deleteNote(noteId);
    }

    public Note findById(Integer noteId){
        return noteMapper.findById(noteId);
    }

    public List<Note> getAllNotes(){
        return noteMapper.getAllNotes();
    }

    public List<Note> getUserNotes(Integer userid){
        return noteMapper.getUserNotes(userid);
    }

}
