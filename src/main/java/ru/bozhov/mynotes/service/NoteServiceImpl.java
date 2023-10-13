package ru.bozhov.mynotes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bozhov.mynotes.model.Note;
import ru.bozhov.mynotes.repository.NoteRepository;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    NoteRepository noteRepository;
    @Override
    public List<Note> getNotes() {
        return noteRepository.findAll();
    }

    @Override
    public Note getNoteByAuthor(String author) {
        Note note = noteRepository.findNoteByAuthor(author);
        if(note!=null)
            return note;
        return null;
    }

    @Override
    public void saveNote(Note note) {
        if(note!=null) {
            try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.valueOf(note.getId())))){
                bufferedWriter.write(note.getText());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            noteRepository.save(note);
        }

    }

    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(null);

    }
}
