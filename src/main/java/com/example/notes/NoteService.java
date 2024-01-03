package com.example.notes;


import com.example.config.JWTService;
import com.example.user.User;
import com.example.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository noteRepository;

    private JWTService jwtService;

    private UserRepository userRepository;

    public void createNewNote(CreateNote createNote, String authHeader) {
        final String jwt = authHeader.substring(7);
        final String username = jwtService.extractUsername(jwt);
        final User user = userRepository.findByUserName(username).get();

        Note note = Note.builder()
                .title(createNote.getTitle())
                .content(createNote.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();
        noteRepository.save(note);
    }
}
