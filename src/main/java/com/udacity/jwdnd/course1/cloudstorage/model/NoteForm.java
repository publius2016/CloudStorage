package com.udacity.jwdnd.course1.cloudstorage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoteForm {
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;
}
