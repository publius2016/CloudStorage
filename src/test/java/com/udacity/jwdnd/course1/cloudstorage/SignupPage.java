package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameInput;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInput;

    @FindBy(id = "inputUsername")
    private WebElement usernameInput;

    @FindBy(id = "inputPassword")
    private WebElement passwordInput;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "login-link")
    private WebElement loginLink;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addFirstName(String message) {
        firstNameInput.clear();
        firstNameInput.sendKeys(message);
    }

    public void addLastName(String message) {
        lastNameInput.clear();
        lastNameInput.sendKeys(message);
    }

    public void addUsername(String message) {
        usernameInput.clear();
        usernameInput.sendKeys(message);
    }

    public void addPassword(String message) {
        passwordInput.clear();
        passwordInput.sendKeys(message);
    }

    public void signUpNewUser(String firstName, String lastName, String username, String password) {
        addFirstName(firstName);
        addLastName(lastName);
        addUsername(username);
        addPassword(password);
        submitSignupForm();
    }

    public void submitSignupForm() {
        submitButton.click();
    }

    public void goToLoginPage() {
        loginLink.click();
    }
}
