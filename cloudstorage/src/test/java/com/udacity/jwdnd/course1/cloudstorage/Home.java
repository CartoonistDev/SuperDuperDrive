package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

    @FindBy(id="logout")
    WebElement logoutBtn;

    @FindBy(id="note-add-btn")
    WebElement noteBtn;

    public Home(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void logoutBtn(){
        logoutBtn.click();
    }

    public void noteBtn(){
        noteBtn.click();
    }
}
