package com.example.notes.noteService;


import com.example.config.JWTService;
import com.example.notes.noteEntities.CreateNote;
import com.example.notes.noteEntities.Note;
import com.example.notes.noteEntities.NoteShare;
import com.example.notes.noteEntities.UpdateNote;
import com.example.notes.noteRepository.NoteRepository;
import com.example.notes.noteRepository.NoteShareRepository;
import com.example.user.userEntities.User;
import com.example.user.userRepository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class NoteService {
    private final NoteRepository noteRepository;

    private final JWTService jwtService;

    private final UserRepository userRepository;

    private final NoteShareRepository noteShareRepository;

    NoteService(NoteRepository noteRepository,
                JWTService jwtService,
                UserRepository userRepository,
                NoteShareRepository noteShareRepository
    ){
        this.noteRepository = noteRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.noteShareRepository = noteShareRepository;
    }

    private String jwt;
    private String username;
    private User user;

    private void fetchUserFromToken(String authHeader){
        jwt = authHeader.substring(7);
        username = jwtService.extractUsername(jwt);
        user = userRepository.findByUserName(username).get();
    }

    public void createNewNote(CreateNote createNote, String authHeader) {
        fetchUserFromToken(authHeader);
        Note note = Note.builder()
                .title(createNote.getTitle())
                .content(createNote.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
        noteRepository.save(note);
    }

    public List<Note> fetchAllNotes(String authHeader) {
        fetchUserFromToken(authHeader);
        return noteRepository.fetchAllByUser(user);
    }

    public Note fetchNodeById(Long noteId, String authHeader) {
        fetchUserFromToken(authHeader);
        return noteRepository.fetchAllByUser(user).stream()
                                                  .filter(note -> note.getNoteId() == noteId)
                                                  .findFirst()
                                                  .get();
    }

    public Note updateExisitngNote(Long noteId, Note note, UpdateNote patch, String authHeader) {
        fetchUserFromToken(authHeader);
        note.setUpdatedAt(LocalDateTime.now());
        note.setTitle(patch.getTitle());
        note.setContent(patch.getContent());
        noteRepository.save(note);
        return fetchNodeById(noteId, authHeader);
    }

    public Note deleteExistingNote(Long noteId, String authHeader) {
        fetchUserFromToken(authHeader);
        Note note = fetchNodeById(noteId, authHeader);
        noteRepository.deleteById(noteId);
        return note;
    }

    public List<Note> searchNotes(String query, String authHeader) {
        fetchUserFromToken(authHeader);
        return noteRepository.searchNotesByUser(user, query);
    }

    public NoteShare shareNote(Long noteId, Integer sharedWithUserId, String authHeader) {
        fetchUserFromToken(authHeader);
        User sharedWithUser = userRepository.findById(sharedWithUserId)
                                            .orElse(null);
        if(sharedWithUserId == null){
            return null;
        }

        Note note = noteRepository.findById(noteId)
                                  .orElse(null);
        if(note == null){
            return null;
        }

        NoteShare noteShare = null;
        if (noteShareRepository.findByNoteAndSharedWithUser(note, sharedWithUser) == null) {
            noteShare = NoteShare.builder()
                                            .note(note)
                                            .sharedWithUser(sharedWithUser)
                                            .build();
        }
        NoteShare savedNoteShare = noteShareRepository.saveAndFlush(noteShare);
        return savedNoteShare;
    }
}
