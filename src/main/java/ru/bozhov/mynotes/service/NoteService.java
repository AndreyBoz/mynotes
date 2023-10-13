package ru.bozhov.mynotes.service;

import ru.bozhov.mynotes.model.Note;

import java.util.List;

public interface NoteService {
    List<Note> getNotes();
    Note getNoteByAuthor(String author);
    void saveNote(Note note);
    void deleteNote(Long id);
    Note getNoteById(Long id);
}
