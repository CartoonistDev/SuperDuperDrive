package com.udacity.jwdnd.course1.cloudstorage.model;

public class Note {
    private String noteDescription;
    private String noteTitle;

    private Integer noteId;
    private Integer userid;

    public Note(){}

    public Note(String noteTitle, String noteDescription,  Integer noteid, Integer userid) {
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.noteId = noteid;
        this.userid = userid;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }


    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }
}
