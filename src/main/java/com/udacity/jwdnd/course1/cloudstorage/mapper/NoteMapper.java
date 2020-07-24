package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface NoteMapper {
    @Select("SELECT noteid, notetitle, notedescription, NOTES.userid FROM NOTES LEFT JOIN USERS ON Notes.userid = USERS.userid WHERE username = #{username}")
    List<Note> getNotes(String username);

    @Insert("INSERT INTO NOTES (notetitle, notedescription, userid) SELECT #{noteTitle}, #{noteDescription}, USERS.userid FROM USERS WHERE USERS.username = #{username}")
    @Options(useGeneratedKeys = true, keyProperty = "noteId")
    int insertNote(Note note);

    @Delete("DELETE FROM NOTES WHERE noteid = #{noteId}")
    int deleteNote(Note note);

    @Update("UPDATE NOTES SET notetitle = #{noteTitle}, noteDescription = #{noteDescription} WHERE noteid = #{noteId}")
    int updateNote(Note note);
}
