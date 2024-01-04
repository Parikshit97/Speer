package com.example.notes.noteController;

import com.example.notes.noteEntities.CreateNote;
import com.example.notes.noteEntities.Note;
import com.example.notes.noteEntities.NoteShare;
import com.example.notes.noteEntities.UpdateNote;
import com.example.notes.noteService.NoteService;
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
    public void createNewNote(@RequestBody CreateNote createNote,
                              @RequestHeader("Authorization") String token) {
        noteService.createNewNote(createNote, token);
    }

    @GetMapping(value = "/notes")
    public List<Note> fetchAllNotes(@RequestHeader("Authorization") String token){
        return noteService.fetchAllNotes(token);
    }

    @GetMapping(value = "/notes/{id}")
    public Note fetchNodeById(@PathVariable(name = "id") Long noteId,
                              @RequestHeader("Authorization") String token) {
        return noteService.fetchNodeById(noteId, token);
    }

    @PutMapping(value = "/notes/{id}")
    public Note updateExisitngNote(@PathVariable(name = "id") Long noteId,
                                   @RequestBody UpdateNote updateNote,
                                   @RequestHeader("Authorization") String token) {
        Note note = fetchNodeById(noteId, token);
        if(note == null){
            return null;
        }else{
            return noteService.updateExisitngNote(noteId, note, updateNote, token);
        }
    }

    @DeleteMapping(value = "/notes/{id}")
    public Note deleteExistingNote(@PathVariable(name = "id") Long noteId,
                                   @RequestHeader("Authorization") String token) {
        return noteService.deleteExistingNote(noteId, token);
    }

    @GetMapping("/search")
    public List<Note> searchNotes(@RequestParam(name = "q") String query,
                                  @RequestHeader("Authorization") String token) {
        List<Note> searchResults = noteService.searchNotes(query, token);
        return searchResults;
    }

    @PostMapping("/notes/{id}/share")
    public NoteShare shareNote(@PathVariable(name = "id") Long noteId,
                               @RequestParam(name = "sharedWithUserId") Integer sharedWithUserId,
                               @RequestHeader("Authorization") String token) {
        return noteService.shareNote(noteId, sharedWithUserId, token);
    }
}
