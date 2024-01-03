package com.example.notes;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class NoteService {
    private NoteRepository noteRepository;

    public void createNewNote(CreateNote createNote) {
        Note note = Note.builder()
                .title(createNote.getTitle())
                .content(createNote.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        noteRepository.save(note);
    }
}
