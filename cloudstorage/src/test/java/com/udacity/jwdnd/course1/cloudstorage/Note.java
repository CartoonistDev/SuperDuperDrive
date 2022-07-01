package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Note {
    @FindBy(id="note-add-btn")
    private WebElement clickNoteAddBtn;
    @FindBy(id="nav-notes-submit")
    private WebElement clickNoteSubmitBtn;

    @FindBy(id="nav-notes-tab")
    private WebElement clickNoteBtn;

    @FindBy(id="note-edit-btn")
    private WebElement clickEditNoteBtn;

    @FindBy(id="note-title")
    protected WebElement addNoteTitle;
    @FindBy(id="note-description")
    protected WebElement addNoteDescription;

    @FindBy(id = "notetitle")
    private WebElement noteTitleData;

    @FindBy(id = "notedescription")
    private WebElement noteDescriptionData;

    @FindBy(id = "note-del-btn")
    private WebElement noteDeleteBtn;

    public Note(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }
    public void clickAddNoteBtn(){
        clickNoteAddBtn.click();
    }
    public void clickSubmitNoteBtn(){
        clickNoteSubmitBtn.click();
    }

    public void clickNoteDeleteBtn(){
        noteDeleteBtn.click();
    }

    public void clickEditNoteBtn(){
        clickEditNoteBtn.click();
    }
    public void openNoteTab(){
        clickNoteBtn.click();
    }
    public void addNoteTitle(){
        addNoteTitle.click();
    }
    public void addNoteDescription(){
        addNoteDescription.click();
    }

    public String getNoteTitle(){
        return noteTitleData.getAttribute("textContent");
    }
    public String getNoteDescription(){
        return noteDescriptionData.getAttribute("textContent");
    }
}
