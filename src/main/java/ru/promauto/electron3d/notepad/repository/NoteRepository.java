package ru.promauto.electron3d.notepad.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.promauto.electron3d.notepad.data.entity.Note;
import ru.promauto.electron3d.notepad.data.entity.Tag;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<List<Note>> findAllByUserIdAndTag(Long userId, Tag tag);
}
