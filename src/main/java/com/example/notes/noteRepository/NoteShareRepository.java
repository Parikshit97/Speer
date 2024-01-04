package com.example.notes.noteRepository;

import com.example.notes.noteEntities.Note;
import com.example.notes.noteEntities.NoteShare;
import com.example.notes.noteEntities.NoteShareId;
import com.example.user.userEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteShareRepository extends JpaRepository<NoteShare, NoteShareId> {
    NoteShare findByNoteAndSharedWithUser(Note note, User sharedWithUser);
}