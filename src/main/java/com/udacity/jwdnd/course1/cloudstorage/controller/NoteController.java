package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import com.udacity.jwdnd.course1.cloudstorage.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NoteController {
    NoteService noteService;

    public NoteController(NoteService noteService) { this.noteService = noteService; }

    @PostMapping("/note")
    public String addNote(NoteForm noteForm, Model model) {
        noteService.addNote(noteForm);
        model.addAttribute("notes", noteService.getNotes());
        return "home";
    }
}
