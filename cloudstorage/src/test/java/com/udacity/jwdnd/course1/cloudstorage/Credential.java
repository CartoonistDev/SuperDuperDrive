package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.xml.xpath.XPathExpressionException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.xpath;

public class Credential {

    @FindBy(id="nav-credentials-tab")
    private WebElement openCredentialTab;

    @FindBy(id="addCredential")
    private WebElement clickCredentialAddBtn;

    @FindBy(id="credentialSubmit")
    private WebElement clickCredentialSubmitBtn;

    @FindBy(id="credential-edit-btn")
    private WebElement clickEditCredentialBtn;

    @FindBy(id="credential-url")
    protected WebElement addCredentialUrl;

    @FindBy(id="credential-username")
    protected WebElement addCredentialUsername;

    @FindBy(id="credential-password")
    protected WebElement addCredentialPassword;

    @FindBy(id = "credentialUrls")
    private WebElement credentialUrlData;

    @FindBy(id = "credentialUsernames")
    private WebElement credentialUsernameData;

    @FindBy(id ="credentialPasswords")
    private WebElement credentialPasswordData;

    @FindBy(id = "credential-del-btn")
    private WebElement credentialDeleteBtn;

    public Credential(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void clickAddCredentialBtn(){
        clickCredentialAddBtn.click();
    }
    public void clickSubmitCredentialBtn(){
        clickCredentialSubmitBtn.click();
    }

    public void clickCredentialDeleteBtn(){
        credentialDeleteBtn.click();
    }
    public void clickEditCredentialBtn(){
        clickEditCredentialBtn.click();
    }
    public void openCredentialTab(){
        openCredentialTab.click();
    }
    public void addCredentialUrl(){
        addCredentialUrl.click();
    }
    public void addCredentialUsername(){
        addCredentialUsername.click();
    }
    public void addCredentialPassword(){
        addCredentialPassword.click();
    }
    public String getCredentialUrl(){
        return credentialUrlData.getAttribute("textContent");
    }
    public String getCredentialUsername(){
        return credentialUsernameData.getAttribute("textContent");
    }
    public String getCredentialPassword(){
        return credentialPasswordData.getAttribute("textContent");
    }
}
