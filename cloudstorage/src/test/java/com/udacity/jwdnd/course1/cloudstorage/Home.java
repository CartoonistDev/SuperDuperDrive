package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Home {

    @FindBy(id="logout")
    WebElement logoutBtn;

    public Home(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }


    public void logoutBtn(){
        logoutBtn.click();
    }
}
