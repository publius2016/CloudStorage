package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

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

    @FindBy(css=".btn-edit")
    private List<WebElement> noteEditButton;

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

    public String getNotesTitleDisplay() {
        return noteTitles.get(0).getText();
    }

    public String getNotesDescriptionDisplay() {
        return noteDescriptions.get(0).getText();
    }

    public void logoutUser() {
        logoutButton.click();
    }

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
