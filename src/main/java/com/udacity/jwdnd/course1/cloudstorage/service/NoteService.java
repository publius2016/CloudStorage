package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.NoteForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private NoteMapper noteMapper;
    private AuthenticationService authenticationService;

    public NoteService(NoteMapper noteMapper, AuthenticationService authenticationService) {
        this.noteMapper = noteMapper;
        this.authenticationService = authenticationService;
    }

    public void addNote(NoteForm noteForm) {
        noteMapper.insertNote(new Note(noteForm.getNoteTitle(), noteForm.getNoteDescription(), authenticationService.getCurrentUsername()));
    }

    public List<Note> getNotes(String username) { return noteMapper.getNotes(username); }

    public void deleteNote(String noteId) { noteMapper.deleteNote(noteId); }
}
