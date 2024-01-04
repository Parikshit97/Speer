package com.example.notes.noteEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable

class SharingDetails {

    @Column(name = "shared_by_user_id")
    private Integer sharedByUserId;

    @Column(name = "shared", nullable = false)
    private boolean shared;
}
