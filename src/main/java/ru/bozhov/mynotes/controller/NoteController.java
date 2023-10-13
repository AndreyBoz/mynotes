package ru.bozhov.mynotes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bozhov.mynotes.model.Note;
import ru.bozhov.mynotes.service.NoteService;

import java.sql.Date;
import java.util.List;

@Controller
public class NoteController {
    final
    NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public String getNotes(Model model){
        List<Note> notes = noteService.getNotes();
        model.addAttribute("notes", notes);
        return "notes";
    }
    @GetMapping("/create")
    public String getCreationPage(){
        return "creating_page";
    }
    @PostMapping("/create")
    public String createNote(@RequestParam String author, @RequestParam Date date, @RequestParam String text) {
        Note newNote = new Note();
        newNote.setAuthor(author);
        newNote.setDate(date);
        newNote.setText(text);

        noteService.saveNote(newNote);

        return "redirect:/notes";
    }
    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id){
        noteService.deleteNote(id);
        return "redirect:/notes";
    }


    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model) {
        Note note = noteService.getNoteById(id);
        model.addAttribute("note", note);
        return "edit_page";
    }

    @PostMapping("/edit/{id}")
    public String editNote(@PathVariable Long id, @RequestParam String author, @RequestParam Date date, @RequestParam String text) {
        Note note = noteService.getNoteById(id);
        note.setAuthor(author);
        note.setDate(date);
        note.setText(text);
        noteService.saveNote(note);

        return "redirect:/notes";
    }
}
