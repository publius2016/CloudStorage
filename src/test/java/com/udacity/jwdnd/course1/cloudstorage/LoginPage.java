package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public void addUsername(String message) {
        usernameInput.clear();
        usernameInput.sendKeys(message);
    }

    public void addPassword(String message) {
        passwordInput.clear();
        passwordInput.sendKeys(message);
    }

    public void loginUser(String username, String password) {
        addUsername(username);
        addPassword(password);
        submitCredentials();
    }

    public void submitCredentials() {
        submitButton.click();
    }

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
