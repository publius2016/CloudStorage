package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Note {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
    private String username;

    public Note(Integer noteId, String noteTitle, String noteDescription, String username) {
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
        this.username = username;
    }
}
