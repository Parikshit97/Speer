package com.example.notes;


import com.example.config.JWTService;
import com.example.user.User;
import com.example.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class NoteService {
    private NoteRepository noteRepository;

    private JWTService jwtService;

    private UserRepository userRepository;

    NoteService(NoteRepository noteRepository,
                JWTService jwtService,
                UserRepository userRepository){
        this.noteRepository = noteRepository;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
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
}
