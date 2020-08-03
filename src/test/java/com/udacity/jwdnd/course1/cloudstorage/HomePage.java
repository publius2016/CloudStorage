package com.udacity.jwdnd.course1.cloudstorage;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class HomePage {
    @FindBy(id="logout-button")
    private WebElement logoutButton;

    @FindBy(id="nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id="add-note-button")
    private WebElement addNoteButton;

    @FindBy(id="note-title")
    private WebElement noteTitleInput;

    @FindBy(id="note-description")
    private WebElement noteDescriptionInput;

    @FindBy(id="note-submit-proxy")
    private WebElement noteSubmit;

    @FindBy(css="tbody tr th")
    private List<WebElement> noteTitles;

    @FindBy(css="tbody tr td:nth-of-type(2)")
    private List<WebElement> noteDescriptions;

    @FindBy(css=".btn-edit-note")
    private List<WebElement> noteEditButton;

    @FindBy(css=".btn-delete-note")
    private List<WebElement> noteDeleteButton;

    @FindBy(id="nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id="add-credentials-button")
    private WebElement addCredentialsButton;

    @FindBy(id="credential-url")
    private WebElement credentialUrlInput;

    @FindBy(id="credential-username")
    private WebElement credentialUsernameInput;

    @FindBy(id="credential-password")
    private WebElement credentialPasswordInput;

    @FindBy(id="credential-submit-proxy")
    private WebElement credentialSubmit;

    @FindBy(css=".btn-edit-credentials")
    private List<WebElement> credentialsEditButton;

    @FindBy(css=".btn-delete-credentials")
    private List<WebElement> credentialsDeleteButton;

    @FindBy(css="tbody tr th")
    private List<WebElement> credentialsUrls;

    @FindBy(css="tbody tr td:nth-of-type(2)")
    private List<WebElement> credentialsUsernames;

    public void selectNotesTab() {
        notesTab.click();
    }

    public void selectAddNote() {
        addNoteButton.click();
    }

    public void submitNewNote(String title, String description) {
        noteTitleInput.clear();
        noteDescriptionInput.clear();

        noteTitleInput.sendKeys(title);
        noteDescriptionInput.sendKeys(description);
        noteSubmit.click();
    }

    public void selectEditNote() {
        noteEditButton.get(0).click();
    }

    public void selectDeleteNote() {
        noteDeleteButton.get(0).click();
    }

    public String getNotesTitleDisplay() {
        return noteTitles.get(0).getText();
    }

    public String getNotesDescriptionDisplay() {
        return noteDescriptions.get(0).getText();
    }

    public void selectCredentialsTab() { credentialsTab.click(); }

    public void selectAddCredentials() { addCredentialsButton.click(); }

    public void submitNewCredentials(String url, String username, String password) {
        credentialUrlInput.clear();
        credentialUsernameInput.clear();
        credentialPasswordInput.clear();

        credentialUrlInput.sendKeys(url);
        credentialUsernameInput.sendKeys(username);
        credentialPasswordInput.sendKeys(password);

        credentialSubmit.click();
    }

    public void selectEditCredential() {
        credentialsEditButton.get(0).click();
    }

    public void selectDeleteCredential() {
        credentialsDeleteButton.get(0).click();
    }

    public String getLastCredentialUrlDisplay() {
        return credentialsUrls
                .get(credentialsUrls.size() - 1)
                .getText();
    }

    public String getLastCredentialsUsernameDisplay() {
        return credentialsUsernames
                .get(credentialsUsernames.size() - 1)
                .getText();
    }

    public void logoutUser() {
        logoutButton.click();
    }

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
