package ru.bozhov.mynotes.repository;

import org.springframework.data.repository.CrudRepository;
import ru.bozhov.mynotes.model.Note;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAll();
    Note findNoteByAuthor(String author);
}
