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

    private final NoteService noteService;
    @PostMapping(value = "/notes")
    public void createNewNote(@RequestBody CreateNote createNote) {
        noteService.createNewNote(createNote);
    }
}
