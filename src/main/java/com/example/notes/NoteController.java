package com.example.notes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.example.security.auth", "com.example.notes"})
public class NoteController {

    private final NoteService noteService;

    /**
        Create a new note for the authenticated user
     */
    @PostMapping(value = "/notes")
    public void createNewNote(@RequestBody CreateNote createNote, @RequestHeader("Authorization") String token) {
        noteService.createNewNote(createNote, token);
    }

    @GetMapping(value = "/notes")
    public List<Note> fetchAllNotes(@RequestHeader("Authorization") String token){
        return noteService.fetchAllNotes(token);
    }

    @GetMapping(value = "/notes/{id}")
    public Note fetchNodeById(@PathVariable(name = "id") Long noteId, @RequestHeader("Authorization") String token) {
        return noteService.fetchNodeById(noteId, token);
    }
}
