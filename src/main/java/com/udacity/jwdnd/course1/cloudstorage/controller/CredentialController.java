package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.CredentialForm;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/credential")
public class CredentialController {
    private NoteService noteService;
    private CredentialService credentialService;
    private AuthenticationService authenticationService;

    public CredentialController(CredentialService credentialService, AuthenticationService authenticationService, NoteService noteService) {
        this.noteService = noteService;
        this.credentialService = credentialService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String addCredential(NoteForm noteForm, CredentialForm credentialForm, Model model) {
        if (credentialForm.getCredentialId() != null) {
            credentialService.updateCredential(credentialForm);
        } else {
            credentialService.addCredential(credentialForm);
        }

        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        model.addAttribute("credentials", credentialService.getCredentials(authenticationService.getCurrentUsername()));
        return "home";
    }

    @GetMapping("/delete/{credentialId}")
    public String deleteCredential(NoteForm noteForm, CredentialForm credentialForm, @PathVariable("credentialId") String credentialId, Model model) {
        credentialService.deleteCredential(credentialId);
        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        model.addAttribute("credentials", credentialService.getCredentials(authenticationService.getCurrentUsername()));
        return "home";
    }
}
