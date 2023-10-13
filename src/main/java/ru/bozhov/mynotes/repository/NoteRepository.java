package ru.bozhov.mynotes.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.bozhov.mynotes.model.Note;

import java.util.List;
@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    List<Note> findAll();
    Note findNoteByAuthor(String author);
}
