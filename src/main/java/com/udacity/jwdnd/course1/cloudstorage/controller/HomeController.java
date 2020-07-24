package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    private NoteService noteService;
    private AuthenticationService authenticationService;

    public HomeController(NoteService noteService, AuthenticationService authenticationService) {
        this.noteService = noteService;
        this.authenticationService = authenticationService;
    }

    @GetMapping
    public String getNote(NoteForm noteForm, Model model) {
        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        return "home";
    }
}
