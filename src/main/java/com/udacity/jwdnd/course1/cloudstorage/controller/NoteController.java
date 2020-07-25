package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/note")
public class NoteController {
    private NoteService noteService;
    private AuthenticationService authenticationService;

    public NoteController(NoteService noteService, AuthenticationService authenticationService) {
        this.noteService = noteService;
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public String addNote(NoteForm noteForm, Model model) {
        if (noteForm.getNoteId() != null) {
            noteService.updateNote(noteForm);
        } else {
            noteService.addNote(noteForm);
        }

        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        return "home";
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(NoteForm noteForm, @PathVariable("noteId") String noteId, Model model) {
        noteService.deleteNote(noteId);
        model.addAttribute("notes", noteService.getNotes(authenticationService.getCurrentUsername()));
        return "home";
    }
}
