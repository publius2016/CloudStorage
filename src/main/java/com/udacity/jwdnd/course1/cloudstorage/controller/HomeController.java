package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private NoteService noteService;
    private AuthenticationService authenticationService;
    private CredentialService credentialService;

    public HomeController(NoteService noteService, AuthenticationService authenticationService, CredentialService credentialService) {
        this.noteService = noteService;
        this.authenticationService = authenticationService;
        this.credentialService = credentialService;
    }

    @GetMapping("/home")
    public String getHome(NoteForm noteForm, CredentialForm credentialForm, Model model) {
        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        model.addAttribute("credentials", credentialService.getCredentials(authenticationService.getCurrentUsername()));
        return "home";
    }
}
