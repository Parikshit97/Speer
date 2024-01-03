package com.example.notes;

import com.example.user.LoginRequest;
import com.example.user.UserController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@ComponentScan(basePackages = {"com.example.security.auth", "com.example.notes"})
public class NoteController {

    private final NoteRepository noteRepository;
    private final UserController userController;
    @PostMapping(value = "/notes")
    public void createNewNote(@RequestBody CreateNote createNote) {
//        log.info("Create Note Request Received : {}", createNote);
//        userController.userLogin(LoginRequest.builder().userName("parikshit3097").password("Parikshit@1997").build());
        Note note = Note.builder()
                        .title(createNote.getTitle())
                        .content(createNote.getContent())
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
        noteRepository.save(note);
    }
}
