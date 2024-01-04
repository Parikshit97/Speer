package com.example.notes.noteEntities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NoteShareId implements Serializable  {
    private Note note;
    private Integer sharedWithUser;
}
