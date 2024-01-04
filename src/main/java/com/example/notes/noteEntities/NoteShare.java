package com.example.notes.noteEntities;

import com.example.user.userEntities.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note_share")
@IdClass(NoteShareId.class)
public class NoteShare {
    @Id
    @ManyToOne
    @JoinColumn(name = "noteId", nullable = false)
    private Note note;

    @Id
    @ManyToOne
    @JoinColumn(name = "shared_with_user_id", nullable = false)
    private User sharedWithUser;
}
