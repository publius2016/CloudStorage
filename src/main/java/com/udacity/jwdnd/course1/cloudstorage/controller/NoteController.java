package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
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

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public String addNote(NoteForm noteForm, Model model) {
        try {
            if (noteForm.getNoteId() != null) {
                noteService.updateNote(noteForm);
                return "redirect:/home?message=You have successfully updated your note.";
            } else {
                noteService.addNote(noteForm);
                return "redirect:/home?message=You have successfully added your note.";
            }
        } catch (Error error) {
            return "redirect:/home?message=Something went wrong and we couldn't save your note.";
        }
    }

    @GetMapping("/delete/{noteId}")
    public String deleteNote(@PathVariable("noteId") String noteId) {
        try {
            noteService.deleteNote(noteId);
            return "redirect:/home?message=You have successfully deleted your note.";
        } catch (Error error) {
            return "redirect:/home?message=Something went wrong and we couldn't delete your note.";
        }
    }
}
