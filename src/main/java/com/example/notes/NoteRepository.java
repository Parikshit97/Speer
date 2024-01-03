package com.example.notes;

import com.example.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    @Query("SELECT n FROM Note n WHERE n.user = :user")
    List<Note> fetchAllByUser(@Param("user") User user);

    @Query("SELECT n FROM Note n WHERE n.user = :user AND (LOWER(n.title) LIKE LOWER(CONCAT('%', :query, '%')) OR LOWER(n.content) LIKE LOWER(CONCAT('%', :query, '%')))")
    List<Note> searchNotesByUser(@Param("user") User user, @Param("query") String query);
}
